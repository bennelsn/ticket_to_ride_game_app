package server.proxies;

import server.poller.ClientCommands;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.client.IGameMenuClientFacade;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 2/12/18.
 */

public class GameMenuClientProxy implements IGameMenuClientFacade {
    @Override
    public void createGame(IGame game) {
        for(String username: ClientCommands.getUsers()) {
            ClientCommands.addCommand(username, _createGameCommand(game));
        }
    }

    @Override
    public void joinGame(IPlayer player, IGame game) {
        for(IPlayer user : game.getPlayers()){
            ClientCommands.addCommand(user.getUsername(), _createJoinCommand(player, game));
        }
    }

    private static final String CLASS = "facade.GameMenuClientFacade";

    private static ICommand _createJoinCommand(IPlayer player, IGame game){
        Class<?>[] parmTypes = {IPlayer.class,IGame.class};
        Object[] parm = {player,game};
        return new Command(CLASS, "joinGame", parmTypes, parm);
    }

    private static ICommand _createGameCommand(IGame game){
        Class<?>[] parmTypes = {IGame.class};
        IGame[] parm = {game};
        return new Command(CLASS, "createGame", parmTypes, parm);
    }
}
