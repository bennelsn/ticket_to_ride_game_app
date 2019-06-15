package server.proxies;

import server.poller.ClientCommands;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.client.IGameClientFacade;
import shared.logging.Level;
import shared.logging.Logger;
import shared.model.interfaces.IGameInfo;

/**
 * Created by bdemann on 3/5/18.
 */

public class GameClientProxy implements IGameClientFacade {
    @Override
    public void updateGameInfo(IGameInfo gameInfo) {
        for(String username : gameInfo.getPlayers()) {
            Logger.log("We are telling " + username + " about an update to the game.", Level.FINNEST);
            ClientCommands.addCommand(username, _createUpdateCommand(gameInfo));
        }
    }

    private static final String CLASS = "facade.GameClientFacade";

    private ICommand _createUpdateCommand(IGameInfo gameInfo) {
        Class<?>[] parmTypes = {IGameInfo.class};
        Object[] parmValues = {gameInfo};
        return new Command(CLASS, "updateGameInfo", parmTypes, parmValues);
    }
}
