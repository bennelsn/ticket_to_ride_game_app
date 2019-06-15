package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.interfaces.IGame;

/**
 * Created by Ben on 2/6/2018.
 */

public class JoinGameResult extends Result {

    private IGame _game;

    public JoinGameResult(IGame joinedGame, boolean success, List<ICommand> clientCommands, String userMessage){
        super(success, clientCommands, userMessage);
        this._game = joinedGame;
    }

    public JoinGameResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public IGame getGame() {
        return _game;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Join Game Result - ");
        sb.append("Success: " + _success);
        sb.append(" | ");
        sb.append("Joined Game: " + _game);
        return sb.toString();
    }
}
