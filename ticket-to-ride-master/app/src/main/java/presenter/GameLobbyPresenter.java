package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import facade.guifacade.LobbyGuiFacade;
import model.ClientRoot;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import view.GameLobbyActivity;
import view.ViewUtilities;

/**
 * Created by mikeporet on 2/12/18.
 */

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer {

    private GameLobbyActivity _activity;

    public GameLobbyPresenter(GameLobbyActivity activity) {
        _activity = activity;
        System.out.println("#############################We are observing the client##########################3");
        ClientRoot.addClientRootObserver(this);
        System.out.println(ClientRoot.getClientGame().isGameStarted());
        if(ClientRoot.getClientGame().isGameStarted()){
            System.out.println("The game has been marked as started");
            //switch to the game activity
            ClientRoot.removeAllObservers();
            _activity.goToGameActivity(false);
        }
    }

    @Override
    public void update(Observable observable, Object o) {

        System.out.println("---------------We are at least getting into this function--------------------");

        ViewUtilities.checkServerStatus(_activity);
        if(ClientRoot.getClientGame() == null){
            //if we have left the game
            _activity.finish();
        }
        else if(ClientRoot.getClientGame().isGameStarted()){
            //switch to the game activity
            ClientRoot.removeAllObservers();
            _activity.goToGameActivity(true);
            System.out.println("----------WE ARE UPDATEING THE game instead of the PLAYERS--------------");
            listPlayers(); //TODO I am putting this here for tests. if it crashes try removing it.
        }
        else{
            //Get the list of players
            listPlayers();
            System.out.println("----------WE ARE UPDATEING THE PLAYERS--------------");
        }
    }

    @Override
    public String leaveGame() {
        return LobbyGuiFacade.leaveGame(ClientRoot.getClientPlayer().getUsername());
    }

    @Override
    public String startGame() {
        return LobbyGuiFacade.startGame(ClientRoot.getClientGame(),ClientRoot.getClientPlayer().getUsername());
    }

    @Override
    public boolean checkNumPlayers() {
        IGame game = ClientRoot.getClientGame();
        int numPlayers = game.getNumberPlayer();
        int maxPlayers = game.getMaxNumberPlayer();
        if (numPlayers==maxPlayers)
            return true;
        else
            return false;
    }

    public void listGame(){
        if(ClientRoot.getClientGame() != null) {
            String name = ClientRoot.getClientGame().getGameName();
            //update the name of the game in the lobby activity
            _activity.updateGameName(name);
        }
    }

    public void listPlayers(){
        if(ClientRoot.getClientGame() != null){

            ArrayList<IPlayer> players = new ArrayList<>(ClientRoot.getClientGame().getPlayers());

            StringBuilder playerList = new StringBuilder();

            for (int i = 0; i < players.size(); i++){
                playerList.append(players.get(i).getUsername());
                if (i != (players.size()-1)){
                    playerList.append(", ");
                }
            }

            //Update the list of players on the lobby activity
            _activity.updatePlayerList(playerList.toString());
        }
    }
}
