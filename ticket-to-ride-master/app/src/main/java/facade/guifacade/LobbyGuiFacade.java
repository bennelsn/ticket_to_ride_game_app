package facade.guifacade;

import model.ClientRoot;
import proxies.LobbyServerProxy;
import shared.model.interfaces.IGame;
import shared.results.Result;
import shared.model.interfaces.IPlayer;
import shared.results.StartGameResult;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class LobbyGuiFacade {

    public static void sendChat() {

    }

    public static String leaveGame(String username){
        LobbyServerProxy proxy = new LobbyServerProxy();
        Result results = proxy.leaveGame(username);
        return _processResults(results);
    }

    public static String startGame(IGame game, String username){
        LobbyServerProxy proxy = new LobbyServerProxy();
        StartGameResult results = proxy.startGame(game, username);
        return _processStartGameResults(results);
    }

    private static String _processStartGameResults(StartGameResult results) {
        if(results == null){
            return "Start Game Result is Null";
        }
        if(results.getCommandSuccess()){
            ClientRoot.getClientGame().startGame();
            ClientRoot.setClientGameInfo(results.getGameInfo());
            ClientRoot.setClientPlayer(results.getPlayer());
        }

        return results.getUserMessage();
    }

    private static String _processResults(Result results) {

        if(results == null){
            return "Leave Game Result is Null";
        }

        if(results.getCommandSuccess()){

            IPlayer player = ClientRoot.getClientPlayer();
            ClientRoot.getGame(player.getGameId()).removePlayer(player);

            //take the game from the root.
            ClientRoot.setClientGame(null);

        }

        return results.getUserMessage();
    }
}
