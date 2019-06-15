package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.logging.Level;
import shared.logging.Logger;
import shared.model.interfaces.IGame;

/**
 * Created by Ben on 2/6/2018.
 */

public class CreateGameResult extends Result {

    private IGame _newGame;

    public CreateGameResult(IGame game, boolean success, List<ICommand> clientCommands) {
        this(game, success, clientCommands, "createGameSuccessfull");
    }

    public CreateGameResult(IGame game, boolean success, List<ICommand> clientCommands, String userMessage){
        super(success, clientCommands, userMessage);
        this._newGame = game;
        Logger.log("CreateGameResult constructor game: " + _newGame, Level.FINNEST);
    }

    public CreateGameResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public IGame getGame() {
        return _newGame;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Create Game Result - ");
        sb.append("Success: " + _success);
        sb.append(" | ");
        sb.append("Created Game: " + _newGame);
        return sb.toString();
    }
}
