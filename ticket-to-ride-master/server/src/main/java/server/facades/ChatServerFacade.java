package server.facades;

import server.model.ServerRoot;
import server.poller.ClientCommands;
import server.poller.ClientNotifications;
import shared.model.Chat;
import shared.model.interfaces.IGame;
import shared.results.ChatResult;
import shared.facades.server.IChatServerFacade;

/**
 * Created by bdemann on 3/3/18.
 */

public class ChatServerFacade implements IChatServerFacade {
    @Override
    public ChatResult sendChat(Chat message) {
        IGame currentGame = ServerRoot.getGame(message.getSpeaker().getCurrentGame());
        ServerRoot.addChat(currentGame, message);
        if(message.getMessage().equals("turn")) {
            currentGame.incrementTurnIndex();
            ClientNotifications.gameUpdated(message.getSpeaker().getUsername());
        }
        ClientNotifications.messageSent(message, currentGame);
        return new ChatResult(ServerRoot.getChats(currentGame.getId()), true, ClientCommands.getCommandList(message.getSpeaker().getUsername()), "Chat sent");
    }
}
