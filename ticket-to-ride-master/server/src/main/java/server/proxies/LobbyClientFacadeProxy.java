package server.proxies;

import server.model.ServerRoot;
import server.poller.ClientCommands;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.client.ILobbyClientFacade;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 2/12/18.
 */

public class LobbyClientFacadeProxy implements ILobbyClientFacade {

    @Override
    public void startGame(IGameInfo gameInfo, IPlayer player) {
        IGame game = ServerRoot.getGame(gameInfo.getGameId());
        for(IPlayer user : game.getPlayers()) {
            ClientCommands.addCommand(user.getUsername(), _createStartGameCommand(gameInfo, user));
        }
    }

    @Override
    public void leaveGame(String username) {
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getGameId());
        for(IPlayer user : game.getPlayers()){
            ClientCommands.addCommand(user.getUsername(), _createLeaveGameCommand(username));
        }
        ServerRoot.getPlayer(username).setGameId(-1);
    }

    private static final String CLASS = "facade.LobbyClientFacade";

    private ICommand _createLeaveGameCommand(String username) {
        Class<?>[] parmTypes = {String.class};
        Object[] parmValues = {username};
        return new Command(CLASS, "leaveGame", parmTypes, parmValues);
    }

    private ICommand _createStartGameCommand(IGameInfo game, IPlayer player) {
        Class<?>[] parmTypes = {IGameInfo.class, IPlayer.class};
        Object[] parmValues = {game, player};
        return new Command(CLASS, "startGame", parmTypes, parmValues);
    }
    
}
