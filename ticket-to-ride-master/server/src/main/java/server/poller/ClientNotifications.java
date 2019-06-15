package server.poller;

import server.model.ServerRoot;
import server.proxies.ChatClientProxy;
import server.proxies.GameClientProxy;
import server.proxies.GameMenuClientProxy;
import server.proxies.LobbyClientFacadeProxy;
import shared.model.Chat;
import shared.model.interfaces.IRoute;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 2/12/18.
 */

public class ClientNotifications {

    private static final ClientNotifications _instance = new ClientNotifications();

    public static void playerJoinedGame(int gameId, String username) {
        new GameMenuClientProxy().joinGame(ServerRoot.getPlayer(username), ServerRoot.getGame(gameId));
    }

    public static void gameCreated(int gameId, String username) {
        new GameMenuClientProxy().createGame(ServerRoot.getGame(gameId));
    }

    public static void playerLeftGame(String username) {
        new LobbyClientFacadeProxy().leaveGame(username);
    }

    public static void gameStarted(IGame game, IPlayer player) {
        new LobbyClientFacadeProxy().startGame(game.getGameInfo(), player);
    }

    public static void messageSent(Chat message, IGame currentGame) {
        new ChatClientProxy().updateChat(message);
    }

    private static void _updateGame(String username){
        IPlayer player = ServerRoot.getPlayer(username);
        IGame game = ServerRoot.getGame(player.getGameId());
        new GameClientProxy().updateGameInfo(game.getGameInfo());
    }

    public static void gameUpdated(String username){
        _updateGame(username);
    }

    public static void playerClaimedRoute(String username, IRoute route) {
        _updateGame(username);
    }

    public static void playerDrewTrainCards(String username) {
        _updateGame(username);
    }

    public static void playerDrewDestinationCards(String username) {
        _updateGame(username);
    }

}
