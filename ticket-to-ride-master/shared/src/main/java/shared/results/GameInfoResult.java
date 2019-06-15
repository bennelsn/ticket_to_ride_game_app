package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.interfaces.IGameInfo;

/**
 * Created by mikeporet on 4/16/18.
 */

public class GameInfoResult extends Result {

    private IGameInfo gameInfo;

    public IGameInfo getGameInfo() {
        return gameInfo;
    }

    public GameInfoResult(IGameInfo gameInfo, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this.gameInfo = gameInfo;
    }

    public GameInfoResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }
}
