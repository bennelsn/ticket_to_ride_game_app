package server.facades;

import server.model.ServerRoot;
import server.poller.ClientCommands;
import shared.command.ICommand;
import shared.logging.Level;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import shared.results.GameInfoResult;
import shared.results.PlayerInfoResult;
import shared.results.Result;
import shared.facades.server.IServerFacade;
import shared.logging.Logger;

/**
 * Created by bdemann on 2/10/18.
 */

public class ServerFacade implements IServerFacade {
    @Override
    public Result getCommands(String username) {
        Logger.log(username + " is getting a list of commands.", Level.SANITY_CHECK);
        Result result = new Result(true, ClientCommands.getCommandList(username), "Got Command List");
        Logger.log("Here is the get commands result", Level.FINNEST);
        Logger.log(result, Level.FINNEST);
        for(ICommand command : result.getClientCommands()) {
            Logger.log("The Command is: " + command, Level.FINE);
        }
        return result;
    }

    @Override
    public GameInfoResult getGameInfo(String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getGameId());
        return new GameInfoResult(game.getGameInfo(), true, ClientCommands.getCommandList(username), "any thing you want you got it anything at all you got it");
    }

    @Override
    public PlayerInfoResult getPlayerInfo(String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        return new PlayerInfoResult(player, true, ClientCommands.getCommandList(username), "any way you want it that's the way you get any way you want it");
    }

}
