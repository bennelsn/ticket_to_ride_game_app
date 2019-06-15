package facade;

import model.ClientRoot;
import shared.facades.client.ILobbyClientFacade;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class LobbyClientFacade implements ILobbyClientFacade {

    @Override
    public void startGame(IGameInfo gameInfo, IPlayer player) {
        //Set the initialized gameInfo.
        ClientRoot.getClientGame().startGame();
        ClientRoot.setClientGameInfo(gameInfo);
        ClientRoot.setClientPlayer(player);
    }

    @Override
    public void leaveGame(String username) {
        IPlayer player = ClientRoot.getClientGame().getPlayer(username);
        ClientRoot.getClientGame().removePlayer(player);
    }
}
