package shared.facades.server;

import shared.results.Result;
import shared.model.interfaces.IGame;
import shared.results.StartGameResult;

/**
 * Created by Ben on 2/6/2018.
 */

public interface ILobbyServerFacade {
    StartGameResult startGame(IGame game, String username);
    Result leaveGame(String username);
}
