package shared.results;

import java.util.ArrayList;
import java.util.List;

import shared.command.ICommand;
import shared.model.EndGameTotals;
import shared.model.Player;
import shared.model.interfaces.IGame;

/**
 * Created by paulinecausse on 3/24/18.
 */

public class GameOverResult extends Result {
    private Player longestRouteWinner;
    private List<EndGameTotals> endGameTotals;

    public GameOverResult(Player longestRouteWinner, boolean success, List<ICommand> clientCommands) {
        super(success, clientCommands, "Got player with longest route");
        this.longestRouteWinner = longestRouteWinner;
//        if(this.longestRouteWinner == null) {
//            longestRouteWinner = new Player();
//        }
    }

    public GameOverResult(List<EndGameTotals> endGameTotals, boolean success, List<ICommand> clientCommands) {
        super(success, clientCommands, "Got list of end game point totals");
        this.endGameTotals = endGameTotals;
    }

    public GameOverResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public String toString() {
        if(longestRouteWinner == null){
            return "No player with longest route yet";
        }
        return longestRouteWinner.getUsername();
    }

    public Player getPlayerWithLongestRoute(){
        return longestRouteWinner;
    }

    public List<EndGameTotals> getEndGameTotals() {
        return endGameTotals;
    }
}
