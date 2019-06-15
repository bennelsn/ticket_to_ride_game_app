package server.facades;

import java.util.List;

import server.facades.traincardstate.FirstDraw;
import server.facades.traincardstate.TrainCardState;
import server.model.ServerRoot;
import server.poller.ClientCommands;
import server.poller.ClientNotifications;
import shared.facades.server.IGameServerFacade;
import shared.model.CityPoint;
import shared.model.DestCardSet;
import shared.model.Color;
import shared.model.DestCard;
import shared.model.TrainCardSet;
import shared.model.initialized_info.Routes;
import shared.model.interfaces.IRoute;
import shared.model.TrainCard;
import shared.model.history.events.ClaimRouteEvent;
import shared.model.history.events.GameEvent;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import shared.results.ClaimRouteResult;
import shared.results.DrawDestCardsResult;
import shared.results.DrawTrainCardsResult;

/**
 * Created by bdemann on 3/4/18.
 */

/**
 * The GameServerFacade processes data coming from the Client before it stores it in the ServerModel
 * classes. It is also in charge of getting the information needed by the Client from the Model.
 * This facade specifically takes care of data that is used for the main GameActivity. It will add
 * cards and routes that have been drawn and claimed to the model and return the associated result.
 */
public class GameServerFacade implements IGameServerFacade {

    private static TrainCardState trainCardState;

    public GameServerFacade() {
        if(trainCardState == null) {
            trainCardState = new FirstDraw(this);
        }
    }



    /**
     * A player can claim a route (an edge connecting two cities) using the right amount of train
     * cards.
     *
     * @param route the Route object representing the routes claimed by a player
     * @param cards the set of cards used to claim the route
     * @param username the username of the player claiming the route as a String
     *
     * @pre route.getLength() == number of cards in cardSet
     * @pre username != null
     * @pre route != null
     * @pre cards != null
     *
     * @post turn goes to the next player
     * @post train card are discarded from player's hand
     * @post discarded trainCards go to the trainCards discard deck
     * @post the players' score is incremented
     * @post event of the action is sent
     *
     * @return returns a ClaimRoute result if the trainCards don't match with the edge explaining
     *         what went wrong
     *         returns null if everything is successful (In this case a new Command object is sent)
     */
    @Override
    public ClaimRouteResult claimRoute(IRoute route, TrainCardSet cards, String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getCurrentGame());


        //TODO implement claiming a route
        //Claim the route
        //Make sure the route is a valid route.
        route = _routeIsValid(route, game);
        if(route != null){
            //Check that the cards are the same color as the route.
            //Double check to see if the route is a double route.
            boolean cardsMatch = false;
            IRoute doubleRoute = _routeIsDouble(route);
            if(doubleRoute == null) {
                cardsMatch = _colorsMatch(cards, route);
            }
            else{
                //If the double matches, then we want that one.
                if( _colorsMatch(cards, doubleRoute) && !_routeIsClaimed(doubleRoute, game) ){
                    route = doubleRoute;
                    cardsMatch = true;
                }
                else{
                    cardsMatch = _colorsMatch(cards, route);
                    if(_routeIsClaimed(route, game)){
                        return new ClaimRouteResult(false, player.getTrainCardHand(),game.getGameInfo(),ClientCommands.getCommandList(username), "Route was already claimed.");
                    }
                }
            }
            if (!cardsMatch) {
                return new ClaimRouteResult(false, player.getTrainCardHand(),game.getGameInfo(), ClientCommands.getCommandList(username), "Cards did not match.");
            }
            boolean sufficientTrains = _playerHasEnoughTrains(route.getLength(), player);
            if(!sufficientTrains){
                return new ClaimRouteResult(false, player.getTrainCardHand(),game.getGameInfo(),ClientCommands.getCommandList(username), "You didn't have enough\ntrains to claim the route.");
            }
            player.incrementScore(route.getLength());
            route = game.claimRoute(route, player.getUsername());

        }
        else{
            return new ClaimRouteResult(false, player.getTrainCardHand(),game.getGameInfo(),ClientCommands.getCommandList(username), "Route was not valid\nor claimed.");
        }

        //Take care of double Route is the game has 3 players or less.
        if(game.getPlayers().size() < 4){
            _blockDoubleRoute(route, game);
        }

        //Add cards to discard pile
        game.discardTrainCards(cards);
        //Adjust the players score
        player.incrementScore(route.getValue());
        //Adjust the number of remaining trains player has.
        player.decrementTrains(route.getLength());

        //Discard the right cards used up.
        for(TrainCard card: cards.getTrainCards()){
            player.discardTrainCard(card);
        }

        game.incrementTurnIndex();

        game.getGameHistory().addEvent(new ClaimRouteEvent(username, route, System.currentTimeMillis()));
        ClientNotifications.playerClaimedRoute(username, route);

        return new ClaimRouteResult(true, player.getTrainCardHand(),game.getGameInfo(),ClientCommands.getCommandList(username), "You claimed a route!\nKeep going!");
    }

    //The incoming route will be claimed. Make sure it's twin is now turned off.
    private void _blockDoubleRoute(IRoute route, IGame game) {
        IRoute routeToBlock = _routeIsDouble(route);
        if(routeToBlock != null){
            //Block it
            game.blockRoute(routeToBlock);
        }
    }

    private boolean _routeIsClaimed(IRoute route, IGame game) {
        String routeStartName = route.getStart().get_name();
        String routeEndName = route.getEnd().get_name();
        CityPoint routeStartPt = route.getStart().get_coordinates();
        CityPoint routeEndPt = route.getEnd().get_coordinates();

        List<IRoute> claimedRoutes = game.getClaimedRoutes();
        for(IRoute claimed : claimedRoutes){
            String claimedStartName = claimed.getStart().get_name();
            String claimedEndName = claimed.getEnd().get_name();
            if((routeStartName.equals(claimedStartName) && routeEndName.equals(claimedEndName)) || (routeStartName.equals(claimedEndName) && routeEndName.equals(claimedStartName))) {
                //Now check the coordinates
                CityPoint claimStartPt = claimed.getStart().get_coordinates();
                CityPoint claimEndPt = claimed.getEnd().get_coordinates();

                if ((routeStartPt.x() == claimStartPt.x())  && (routeStartPt.y() == claimStartPt.y()) && (routeEndPt.x() == claimEndPt.x())  && (routeEndPt.y() == claimEndPt.y())) {
                    //If the coordinates are all equal, then we know it's the same.
                    return true;
                }

            }
        }

        return false;
    }


    private boolean _playerHasEnoughTrains(int length, IPlayer player) {
        if(length <= player.getTrains().size()){
            return true;
        }
        else{
            return false;
        }

    }

    private IRoute _routeIsValid(IRoute route, IGame game){
        if(Routes.instance().isRouteValid(route)){
            return game.isRouteAvailable(route);
        }
        else {
            return null;
        }
    }

    //This method will check to see if the route given is a double route, and if it is it will return its counterpart route.
    private IRoute _routeIsDouble(IRoute route) {
        return Routes.instance().isRouteDouble(route);
    }




    /**
     * Checks the color of the train cards and of the route claimed to see if they match.
     *
     * @param cards the set of cards used to claim the route
     * @param route the Route object representing the routes claimed by a player
     *
     * @pre route.getLength() == number of cards in cardSet
     * @pre route != null
     * @pre cards != null
     * @pre both cards and route's color parameter != null
     *
     * @post none
     *
     * @return returns a boolean value stating whether the color of the cards and the route match
     * or not.
     */
    private boolean _colorsMatch(TrainCardSet cards, IRoute route) {

        if(cards.getTrainCards().size() != route.getLength()){
            return false;
        }
        if (cards.colorsMatch()) {
            if(route.getColor().equals(Color.GRAY)) {
                return true;
            } else {
                Color cardColor = cards.getSetColor();
                return cardColor.equals(route.getColor()) || cardColor.equals(Color.RAINBOW);
            }
        }
        return false;
    }


    /**
     * Discards the Destination Card that wasn't chosen by a player
     *
     * @param username the username of the player claiming the route as a String
     * @param keptCards Destination cards that are kept by the player
     * @param discardCards Destination cards that are discarded by player
     *
     * @pre 2 <= keptCard.size() <= 3
     * @pre 0 <= discardCards <= 1
     * @pre username != null
     * @pre keptCards != null
     * @pre discardCards != null
     *
     * @post player's destCard count += 2 or 3
     * @post DestCard discard deck.size() += 0 or 1
     * @post Event of the action is sent
     * @post Command is sent
     *
     * @return returns a Result Object with a message about the success of the action
     */
    @Override
    public DrawDestCardsResult discardDestCards(String username, DestCardSet keptCards, DestCardSet discardCards) {
        // add kept cards to user cards
        IPlayer player = ServerRoot.getPlayer(username);
//        ServerRoot.getPlayer(username).addDestCards(keptCards.toList());
        //player.addDestCards(keptCards.toList());
        // add discarded cards to discard

        //Update dest cards of the player in the game
        System.out.println("This player is discardDestCarding: " + player.getUsername());
        ServerRoot.getGame(player.getGameId()).updatePlayerDestCard(player,keptCards.toList());

        //Update game history
        ServerRoot.getGame(player.getGameId()).getGameHistory().addEvent(new GameEvent(username, "kept " + keptCards.size() + " cards", System.currentTimeMillis()));

        //Notify other users
        ClientNotifications.gameUpdated(username);

        ServerRoot.getGame(player.getGameId()).getDestCardDeck().discard(discardCards.toList());
        return new DrawDestCardsResult(player.getDestCards(),true, ClientCommands.getCommandList(username), "discarded successfully");
    }

    /**
     * When a player draws a train card from the faceUpDeck. A card is added to his/her hand
     * and the same card is taken from the deck.
     *
     * @param username the username of the player claiming the route as a String
     * @param trainCardIndex the index of the train card to be drawn
     *
     * @pre username != null
     * @pre trainCard != null
     *
     * @post FaceUpDeck.cardCount -= 1
     * @post player.trainCards += 1
     * @post trainCard is added to player's hand
     * @post trainCard is removed from faceUpDeck
     *
     * @return
     */
    @Override
    public DrawTrainCardsResult drawFaceUpTrainCard(String username, int trainCardIndex) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getGameId());

        //Figure out if its the players turn
        List<IPlayer> players = game.getPlayers();
        int playerIndex = players.indexOf(player);
        int turnIndex = game.getTurnIndex();
        if(playerIndex != turnIndex) {
            return new DrawTrainCardsResult(ClientCommands.getCommandList(username), "It's not your turn.");
        }

        TrainCard result = trainCardState.drawFaceUpCard(game, trainCardIndex);
        if(result == null) {
            return new DrawTrainCardsResult(ClientCommands.getCommandList(username), "You can't draw a two face up cards if one is a locomotive");
        }
        //player.addTrainCard(result);
        game.updatePlayerTrainCard(player, result);
        game.getCardsFaceUp().set(trainCardIndex, drawCardFromDeck(game.getId()));

        //shuffle away any time we have 3+ locomotive cards.
        while(threeLocomotiveCards(game)){
            for(int i = 0; i < 5; i++) {
                game.discardTrainCard(game.getCardsFaceUp().get(i));
                game.getCardsFaceUp().set(i, drawCardFromDeck(game.getId()));
            }
        }

        game.getGameHistory().addEvent(new GameEvent(username, "drew " + result.toString(), System.currentTimeMillis()));
        ClientNotifications.gameUpdated(username);

        shuffleTrainCards(game);

        return new DrawTrainCardsResult(result, game.getCardsFaceUp(), true, ClientCommands.getCommandList(username), "Drew a face up card");
    }

    private boolean threeLocomotiveCards(IGame game) {
        int locoCount = 0;
        for(TrainCard trainCard : game.getCardsFaceUp()) {
            if(trainCard.getColor().equals(Color.RAINBOW)){
                locoCount++;
            }
        }
        return locoCount >= 3;
    }


    /**
     * When a player draws a train card from the face down Deck. A card is added to his/her hand
     * and the same card is taken from the deck.
     *
     * @param username the username of the player claiming the route as a String
     *
     * @pre username != null
     *
     * @post FaceDownDeck.cardCount -= 1
     * @post player.trainCards += 1
     * @post trainCard is added to player's hand
     * @post trainCard is removed from faceDown deck
     * @post turn goes to the next player
     * @post Event of the action is sent
     *
     * @return returns a DrawCardResult with a message about the success of the action
     */
    @Override
    public DrawTrainCardsResult drawFaceDownTrainCard(String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getCurrentGame());

        TrainCard drawnCard = trainCardState.drawFaceDownCard(game);

        game.updatePlayerTrainCard(player, drawnCard);
        game.getGameHistory().addEvent(new GameEvent(username, "drew a train card", System.currentTimeMillis()));
        ClientNotifications.playerDrewTrainCards(username);

        shuffleTrainCards(game);

        return new DrawTrainCardsResult(drawnCard, game.getCardsFaceUp(), true, ClientCommands.getCommandList(username), "Draw a train card");
    }

    private void shuffleTrainCards(IGame game) {
        int cardsLeft = game.getTrainCardDeck().size();
        if(cardsLeft == 0) {
            game.getTrainCardDeck().shuffleDiscardPile();
        }

    }

    /**
     * A player draws destination cards
     *
     * @param username the username of the player claiming the route as a String
     *
     * @pre username != null
     *
     * @post destCard deck.size() -= 3
     * @post turn goes to the next player
     * @post Event of action is sent
     * @post Command is sent
     *
     * @return returns s DrawCardsResult with a message about the success of the action
     */
    @Override
    public DrawDestCardsResult drawDestCards(String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getCurrentGame());

        List<DestCard> cards = game.getDestCardDeck().draw(3);

        game.incrementTurnIndex();

        game.getGameHistory().addEvent(new GameEvent(username, "drew three destination card", System.currentTimeMillis()));
        ClientNotifications.playerDrewDestinationCards(username);
        ClientNotifications.gameUpdated(username);
        return new DrawDestCardsResult(cards, true, ClientCommands.getCommandList(username), "Draw three destination card");
    }

    public TrainCard drawCardFromDeck(int gameId) {
        IGame game = ServerRoot.getGame(gameId);
        return game.getTrainCardDeck().draw(1).get(0);
    }

    public void setState(TrainCardState state) {
        this.trainCardState = state;
    }
}
