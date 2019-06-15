package server.proxies;

import server.model.ServerRoot;
import server.poller.ClientCommands;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.client.IChatClientFacade;
import shared.logging.Level;
import shared.logging.Logger;
import shared.model.Chat;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 3/5/18.
 */

public class ChatClientProxy implements IChatClientFacade {
    @Override
    public void updateChat(Chat message) {
        IGame game = ServerRoot.getGame(message.getSpeaker().getGameId());
        for(IPlayer player : game.getPlayers()) {
            Logger.log("We are telling " + player.getUsername() + " about a new chat.", Level.FINNEST);
            ClientCommands.addCommand(player.getUsername(), _createUpdateChat(message));
        }
    }

    private static final String CLASS = "facade.ChatClientFacade";

    private ICommand _createUpdateChat(Chat message) {
        Class<?>[] parmTypes = {Chat.class};
        Object[] parmValues = {message};
        return new Command(CLASS, "updateChat", parmTypes, parmValues);

    }
}
