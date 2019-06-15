package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.interfaces.IPlayer;

/**
 * Created by mikeporet on 4/16/18.
 */

public class PlayerInfoResult extends Result {

    private IPlayer gameInfo;

    public IPlayer getPlayerInfo() {
        return gameInfo;
    }

    public PlayerInfoResult(IPlayer playerInfo, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this.gameInfo = playerInfo;
    }

    public PlayerInfoResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }
}
