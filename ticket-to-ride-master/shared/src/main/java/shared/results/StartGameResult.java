package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 3/5/18.
 */

public class StartGameResult extends Result {

    private IPlayer player;
    private IGameInfo gameInfo;

    public StartGameResult(IPlayer player, IGameInfo gameInfo, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this.player = player;
        this.gameInfo = gameInfo;
    }

    public StartGameResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public IPlayer getPlayer() {
        return player;
    }

    public IGameInfo getGameInfo() {
        return gameInfo;
    }
}
