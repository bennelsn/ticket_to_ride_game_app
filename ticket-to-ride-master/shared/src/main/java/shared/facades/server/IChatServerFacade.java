package shared.facades.server;

import shared.model.Chat;
import shared.results.ChatResult;
import shared.results.Result;

/**
 * Created by bdemann on 2/28/18.
 */

public interface IChatServerFacade {

    public ChatResult sendChat(Chat message);

}
