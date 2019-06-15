package facade;

import model.ClientRoot;
import shared.facades.client.IGameMenuClientFacade;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class GameMenuClientFacade implements IGameMenuClientFacade {

    @Override
    public void createGame(IGame game) {
        ClientRoot.addToGameList(game);
    }

    @Override
    public void joinGame(IPlayer player, IGame game) {
        ClientRoot.getListGames().get(game.getId()).addPlayer(player);

        System.out.println(player.toString() + " is getting added to the client root");

        if(ClientRoot.getClientGame() != null){
            if(game.getId() == ClientRoot.getClientGame().getId()){
                ClientRoot.getClientGame().addPlayer(player);
                ClientRoot.somethingChanged();
                System.out.println(player.toString() + " is added to the client root");
            }
        }
    }
}
