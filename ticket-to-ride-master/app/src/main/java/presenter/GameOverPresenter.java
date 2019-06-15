package presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import facade.guifacade.GameOverGuiFacade;
import model.ClientRoot;
import shared.model.EndGameTotals;
import shared.model.TrainCard;
import shared.model.interfaces.IGameInfo;
import view.GameOverActivity;
import view.ViewUtilities;

/**
 * Created by mikeporet on 3/23/18.
 */


public class GameOverPresenter implements Observer{

    GameOverActivity activity;

    public GameOverPresenter(GameOverActivity activity) {
        this.activity = activity;
//        getLongestRoute();
        ClientRoot.addClientRootObserver(this);
        updateActivity();
    }

    @Override
    public void update(Observable observable, Object o) {

        ViewUtilities.checkServerStatus(activity);

        if(ClientRoot.getClientGameInfo() != null){
            updateActivity();
        }
        if(ClientRoot.getClientPlayer() != null){
            updateActivity();
        }

    }

    public void updateActivity(){

        IGameInfo gameInfo = ClientRoot.getClientGameInfo();
        List<EndGameTotals> endGameTotals = ClientRoot.getEndGameTotals();

        if(gameInfo.getPlayers() == null || gameInfo.getPlayerHandSizes() == null || gameInfo.getPlayerPoints() == null
                || gameInfo.getClaimedRoutes() == null || gameInfo.getRemainingTrains() == null){
            System.out.println("Game Info null!");
        }
        else{
            //Set player names
            List<String> players = new ArrayList<>(gameInfo.getPlayers());
            //Fill extra player slots
            for (int i = players.size(); i < 5; i++){
                players.add("-");
            }
            activity.set_player_names(players);
            EndGameTotals gameTotals = new EndGameTotals();
            for(int i = gameInfo.getPlayers().size(); i < players.size(); i++){

                endGameTotals.add(gameTotals);
            }

            System.out.println();

            //Set Point Values and keep track of winner
            List<String> destination_points = new ArrayList<>();
            List<String> unclaimed_points = new ArrayList<>();
            List<String> route_points = new ArrayList<>();
            List<String> longest = new ArrayList<>();
            List<String> totals = new ArrayList<>();

            int winner = 0;
            int best_score = -500;

            for (int i = 0; i < players.size(); i++){
                EndGameTotals current_end_game = endGameTotals.get(i);
                destination_points.add(Integer.toString(current_end_game.getDestination_card_points()));
                unclaimed_points.add(Integer.toString(current_end_game.getUnclaimed_destination_points()));
                route_points.add(Integer.toString(current_end_game.getClaimed_route_points()));
                longest.add(Integer.toString(current_end_game.getLongest_route_bonus()));
                totals.add(Integer.toString(current_end_game.getTotal_points()));
                
                if(i < ClientRoot.getClientGame().getNumberPlayer()){
                    if (current_end_game.getTotal_points() > best_score){
                        best_score = current_end_game.getTotal_points();
                        winner = i;
                    }
                }
            }

            activity.set_destination_points(destination_points);
            activity.set_unclaimed_points(unclaimed_points);
            activity.set_route_points(route_points);
            activity.set_longest(longest);
            activity.set_totals(totals);
            activity.set_winner(winner);

//            activity._updateGameInfo(gameInfo.getPlayers(),gameInfo.getPlayerPoints(),
//                    gameInfo.getClaimedRoutes(), gameInfo.getRemainingTrains(), gameInfo.getPlayerColors(), gameInfo.getPlayerDestCount());


        }
    }
}
