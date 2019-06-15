package proxies;

import model.ClientRoot;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.server.IGameServerFacade;
import shared.model.DestCardSet;
import shared.model.TrainCardSet;
import shared.model.interfaces.IRoute;
import shared.results.ClaimRouteResult;
import shared.results.DrawDestCardsResult;
import shared.results.DrawTrainCardsResult;
import shared.results.Result;
import tasks.TaskExecutor;

/**
 * The GameServerProxy implements the IGameServerFacade
 *
 */
public class GameServerProxy implements IGameServerFacade {

    /**
     * Claims a route and returns the result of the claimed route
     *
     * @param route Routes being claimed
     * @param cards Cards being used to claim the route
     * @param username Username of the player claiming the route
     *
     * @pre route != null && route is not already claimed.
     * @pre cards != null && cards are sufficient number and correct color for the route.
     * @pre username is a real player in the game
     *
     * @post route is claimed.
     * @return ClaimRouteResult the results of the action.
     */
    @Override
    public ClaimRouteResult claimRoute(IRoute route, TrainCardSet cards, String username) {
        Class<?>[] parmTypes = {IRoute.class, TrainCardSet.class, String.class};
        Object[] parmValues = {route, cards, username};

        ICommand command = _generateGameServerFacadeCommand("claimRoute", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()){
            return new ClaimRouteResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (ClaimRouteResult) result;
    }

    /**
     *
     * @param username Username of a player in the game
     * @param trainCardIndex A train card from the player
     * @return DrawCardsResult
     */
    @Override
    public DrawTrainCardsResult drawFaceUpTrainCard(String username, int trainCardIndex) {
        Class<?>[] parmTypes = {String.class, int.class};
        Object[] parmValues = {username, trainCardIndex};
        ICommand command = _generateGameServerFacadeCommand("drawFaceUpTrainCard", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());
        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()) {
            return new DrawTrainCardsResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (DrawTrainCardsResult) result;
    }

    /**
     * Discards destination cards that the player doesn't want to keep.
     *
     * @param username Username of the player discarding the cards.
     * @param keptCards Cards that the given user is keeping.
     * @param discardCards Cards that the given user is discarding.
     *
     * @pre keptCards != null
     * @pre discardCards != null
     * @pre username is a real player in the game
     *
     *
     * @post destination cards are discarded from the given player's hand.
     * @return Result of the discard.
     */
    @Override
    public DrawDestCardsResult discardDestCards(String username, DestCardSet keptCards, DestCardSet discardCards) {
        Class<?>[] parmTypes = {String.class, DestCardSet.class, DestCardSet.class};
        Object[] parmValues = {username, keptCards, discardCards};
        ICommand command = _generateGameServerFacadeCommand("discardDestCards", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()){
            return new DrawDestCardsResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (DrawDestCardsResult) result;
    }

    /**
     * Draws a train card from the deck and gives it to the specified player
     *
     * @param username Username of the player drawing a Train card
     *
     * @pre username is a real user in the game.
     *
     * @post the player's train cards will be +1
     * @return DrawCardResult from the action.
     */
    @Override
    public DrawTrainCardsResult drawFaceDownTrainCard(String username) {
        Class<?>[] parmTypes = {String.class};
        Object[] parmValues = {username};

        ICommand command = _generateGameServerFacadeCommand("drawFaceDownTrainCard", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()){
            return new DrawTrainCardsResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (DrawTrainCardsResult) result;
    }

    /**
     * Draws a ticket (Destination) card from the deck and gives it to the specified player.
     *
     * @param username Username of the player drawing a Ticket (Destination) card.
     *
     * @pre the username is the name of an actual player in the game.
     *
     * @post the player's destination cards will be +1
     * @return DrawCardsResult from the action
     */
    @Override
    public DrawDestCardsResult drawDestCards(String username) {
        Class<?>[] parmTypes = {String.class};
        Object[] parmValues = {username};

        ICommand command = _generateGameServerFacadeCommand("drawDestCards", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()){
            return new DrawDestCardsResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (DrawDestCardsResult) result;
    }

    /**
     * Generates a generic game server command.
     *
     * @param method Method to be invoked.
     * @param parmTypes Parameter Types to be used.
     * @param parmValues Parameter Values to be used.
     *
     * @pre method is a valid method from a valid class.
     * @pre parmTypes are valid parameter types.
     * @pre parmValues are valid values of the given parameter types.
     *
     * @post ICommand command will be returned.
     * @return ICommand to be sent to the server
     */
    private ICommand _generateGameServerFacadeCommand(String method, Class<?>[] parmTypes, Object[] parmValues){
        return new Command("server.facades.GameServerFacade", method, parmTypes, parmValues);
    }
}
