package shared.facades.client;

import shared.model.interfaces.IGame;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

/**
 * This is an interface that will be shared by the CLIENT PROXY CLASSES on the server, and the
 * CLIENT FACADE CLASSES on the client.
 *
 * Created by BenNelson on 2/3/18.
 */

public interface ILobbyClientFacade {
    void startGame(IGameInfo gameInfo, IPlayer player);
    void leaveGame(String username);
}
