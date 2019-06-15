package facade;

import model.ClientRoot;
import shared.facades.client.IGameClientFacade;
import shared.model.interfaces.IGameInfo;

/**
 * Created by bdemann on 3/5/18.
 */

public class GameClientFacade implements IGameClientFacade {

    public void updateGameInfo(IGameInfo gameInfo){
        ClientRoot.setClientGameInfo(gameInfo);
    }

}
