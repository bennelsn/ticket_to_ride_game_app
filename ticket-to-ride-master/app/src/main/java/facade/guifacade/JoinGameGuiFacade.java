package facade.guifacade;

import model.ClientRoot;
import proxies.GameMenuServerProxy;
import shared.logging.Logger;
import shared.results.JoinGameResult;
import shared.facades.server.IGameMenuServerFacade;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import shared.results.Result;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class JoinGameGuiFacade {

    public static String joinGame(int gameId) {
        IGameMenuServerFacade menuServerFacade = new GameMenuServerProxy();
        String player = ClientRoot.getClientPlayer().getUsername();

        if(player == null){

            return "Could not find player";
        }

        Result commandResult = (Result) menuServerFacade.joinGame(gameId, player);

        if(commandResult.getCommandSuccess()){
            JoinGameResult joinGameResult = (JoinGameResult) commandResult;
            _addGame(joinGameResult.getGame());
            //TODO ben demann feels uneasy with this because we are just putting in the same player we had....hmmmm..what's the point?
            _addPlayer(joinGameResult.getGame(), ClientRoot.getClientPlayer());
            _setPlayersGameId(joinGameResult.getGame());
        }

        return commandResult.getUserMessage();
    }


    private static void _addPlayer(IGame game, IPlayer player){
        ClientRoot.getGame(game.getId()).addPlayer(player);
    }

    private static void _setPlayersGameId(IGame game){
        ClientRoot.getClientPlayer().setGameId(game.getId());
    }


    private static void _addGame(IGame game){
        Logger.log(game.isGameStarted());
        ClientRoot.setClientGame(game);
    }
}
