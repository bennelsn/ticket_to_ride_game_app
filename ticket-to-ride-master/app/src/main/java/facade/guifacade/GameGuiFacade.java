package facade.guifacade;

import java.util.ArrayList;
import java.util.List;

import model.ClientRoot;
import proxies.GameServerProxy;
import shared.command.Command;
import shared.model.DestCardSet;
import shared.model.DestCard;
import shared.model.TrainCard;
import shared.model.TrainCardSet;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IRoute;
import shared.results.ClaimRouteResult;
import shared.results.DrawDestCardsResult;
import shared.results.DrawTrainCardsResult;

/**
 * This helps the presenters talk to the model
 * Created by BenNelson on 3/7/18.
 */

public class GameGuiFacade {


    public static List<DestCard> getStarterDestinationCards(){
        DestCardSet s = ClientRoot.getClientPlayer().getUnresolvedDestCards();
        return s.toList();
    }

    public static List<DestCard> getPlayerDestCards(){
        return ClientRoot.getClientPlayer().getDestCards();
    }

    public static IGameInfo getStarterGameInfo(){
        return ClientRoot.getClientGameInfo();
    }

    public static List<TrainCard> getStarterPlayerHand(){
        return ClientRoot.getClientPlayer().getTrainCardHand().get_cards();
    }

    public static List<DestCard> drawDestinationCards() {
        GameServerProxy gsp = new GameServerProxy();
        DrawDestCardsResult cardResults = gsp.drawDestCards(ClientRoot.getClientPlayer().getUsername());
        return _processDrawDestinationResults(cardResults);
    }

    private static List<DestCard> _processDrawDestinationResults(DrawDestCardsResult cardResults) {
        if(cardResults != null){
            ClientRoot.getClientPlayer().setUnresolvedDestCards(cardResults.getCards());
            return cardResults.getCards();
        }
        else {
            return null;
        }
    }

    public static void discardDestinationCards(List<DestCard> chosenDestCards, DestCardSet cardsToRemove){
        GameServerProxy gsp = new GameServerProxy();
        ClientRoot.getClientPlayer().addDestCards(chosenDestCards) ;
        DestCardSet destCardSet = new DestCardSet(chosenDestCards);
        DrawDestCardsResult destCardsResult = gsp.discardDestCards(ClientRoot.getClientPlayer().getUsername(), destCardSet, cardsToRemove);
        try {
            Command.executeList(destCardsResult.getClientCommands());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Boolean> getCompleteDestination(){
        boolean isCompleted = false;
        List<Boolean> completedDestination = new ArrayList<>();
        for(DestCard destCard : ClientRoot.getClientPlayer().getDestCards()){
            if(destCard.isCompleted()){
                isCompleted = true;
            }
            else if(!destCard.isCompleted()){
                isCompleted = false;
            }
            completedDestination.add(isCompleted);
        }

        return completedDestination;
    }

    public static String drawFaceDownTrainCard() {
        GameServerProxy gsp = new GameServerProxy();
        DrawTrainCardsResult cardsResult = gsp.drawFaceDownTrainCard(ClientRoot.getClientPlayer().getUsername());
        try {
            Command.executeList(cardsResult.getClientCommands());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(cardsResult.getCommandSuccess()){
            ClientRoot.getClientPlayer().addTrainCard(cardsResult.getDrawnCard());
            return cardsResult.getDrawnCard().toString();
        }
        return cardsResult.getUserMessage();
    }

    public static String drawFaceUpTrainCard(int trainCardIndex) {
        GameServerProxy gsp = new GameServerProxy();
        DrawTrainCardsResult result = gsp.drawFaceUpTrainCard(ClientRoot.getClientPlayer().getUsername(), trainCardIndex);
        try {
            Command.executeList(result.getClientCommands());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result.getCommandSuccess()){
            ClientRoot.getClientGame().setCardsFaceUp(result.getFaceUpCards());
            ClientRoot.getClientPlayer().addTrainCard(result.getDrawnCard());
        }
        return result.getUserMessage();
    }

    public static String claimRoute(IRoute route, TrainCardSet cards, String username){

        GameServerProxy gameServerProxy = new GameServerProxy();
        ClaimRouteResult result = gameServerProxy.claimRoute(route,cards,username);
        try{
            Command.executeList(result.getClientCommands());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ClientRoot.getClientPlayer().setTrainCards(result.getHand());
        ClientRoot.setClientGameInfo(result.getGameInfo());
        return result.getUserMessage();


       // return null;

    }

    public static boolean checkTurn(){
        String username = ClientRoot.getClientPlayer().getUsername();
        if(username.equals(ClientRoot.getClientGameInfo().activePlayer())){
            return true;
        }

        return false;
    }
}
