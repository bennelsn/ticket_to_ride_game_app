package presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import facade.guifacade.GameGuiFacade;
import model.ClientRoot;
import shared.model.DestCard;
import shared.model.TrainCard;
import shared.model.interfaces.IGameInfo;
import view.GameInfoActivity;
import view.ViewUtilities;

/**
 * Created by paulinecausse on 3/7/18.
 */

public class GameInfoPresenter implements IGameInfoPresenter, Observer {
    private GameInfoActivity _gameInfoActivity;


    public GameInfoPresenter(GameInfoActivity gameInfoActivity){
        this._gameInfoActivity = gameInfoActivity;
        ClientRoot.addClientRootObserver(this);
    }

    @Override
    public void update(Observable obs, Object o) {

        System.out.println("GameInfo Update called\n");

        ViewUtilities.checkServerStatus(_gameInfoActivity);
        if(ClientRoot.getClientGameInfo() != null){
            _updateActivity();
        }
        if(ClientRoot.getClientPlayer() != null){
            _updateActivity();
        }
    }

    @Override
    public IGameInfo getStarterGameInfo() {
        return GameGuiFacade.getStarterGameInfo();
    }


    public void _updateActivity(){
        IGameInfo gameInfo = ClientRoot.getClientGameInfo();
        if(gameInfo.getPlayers() == null || gameInfo.getPlayerHandSizes() == null || gameInfo.getPlayerPoints() == null
                || gameInfo.getClaimedRoutes() == null || gameInfo.getRemainingTrains() == null){
            System.out.println("Game Info null!");
        }
        else{
            _gameInfoActivity._updateGameInfo(gameInfo.getPlayers(),gameInfo.getPlayerPoints(),gameInfo.getPlayerHandSizes(),
                    gameInfo.getClaimedRoutes(), gameInfo.getRemainingTrains(), gameInfo.getPlayerColors(), gameInfo.getPlayerDestCount());

        }

        List<TrainCard> playerHand = ClientRoot.getClientPlayer().getTrainCardHand().get_cards();
        if(playerHand.size() == 0){
            System.out.println("No cards!");
        }
        else{
            _gameInfoActivity._updatePlayerHand(playerHand);
        }
    }

    @Override
    public List<TrainCard> getStarterPlayerHand(){
        return GameGuiFacade.getStarterPlayerHand();
    }


    public List<DestCard> getDestinationCards(){

        //Gets a list of the destination cards in a player's hand
        List<DestCard> playerDestCard = GameGuiFacade.getPlayerDestCards();
        return playerDestCard;
//        return new ArrayList<DestCard>();
    }

    public List<Boolean> getCompleteDestinations(){

        List<Boolean> completedDestination = GameGuiFacade.getCompleteDestination();
        return completedDestination;
//        return new ArrayList<Boolean>();
    }
}
