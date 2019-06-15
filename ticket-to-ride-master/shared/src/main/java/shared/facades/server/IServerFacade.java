package shared.facades.server;

import shared.results.GameInfoResult;
import shared.results.PlayerInfoResult;
import shared.results.Result;

/**
 * Created by bdemann on 2/10/18.
 */

public interface IServerFacade {
    public Result getCommands(String username);
    public GameInfoResult getGameInfo(String username);
    public PlayerInfoResult getPlayerInfo(String username);
}
