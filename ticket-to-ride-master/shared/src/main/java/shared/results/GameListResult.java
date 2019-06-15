package shared.results;

import java.util.ArrayList;
import java.util.List;

import shared.command.ICommand;
import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 2/12/18.
 */

public class GameListResult extends Result {
    private List<IGame> _games;

    public GameListResult(List<IGame> games, boolean success, List<ICommand> clientCommands) {
        super(success, clientCommands, "Got list of games");
        this._games = games;
        if(this._games == null) {
            _games = new ArrayList<>();
        }
    }

    public GameListResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game List Results - ");
        sb.append(" | ");
        for(IGame game : _games) {
            sb.append(game.toString());
            sb.append(" | ");
        }
        return sb.toString();
    }

    public List<IGame> getGameList(){
        return _games;
    }
}
