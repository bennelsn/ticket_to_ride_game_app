package proxies;

import java.util.concurrent.ExecutionException;

import model.ClientRoot;
import shared.command.Command;
import shared.command.ICommand;
import shared.model.Chat;
import shared.results.ChatResult;
import shared.results.Result;
import shared.facades.server.IChatServerFacade;
import tasks.CommandTask;
import tasks.TaskExecutor;

/**
 * Created by bdemann on 3/3/18.
 */

public class ChatServerProxy implements IChatServerFacade{
    @Override
    public ChatResult sendChat(Chat message) {
        Class<?>[] parmTypes = {Chat.class};
        Object[] parmValues = {message};
        ICommand sendChatCommand = new Command("server.facades.ChatServerFacade", "sendChat", parmTypes, parmValues);
        sendChatCommand.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(sendChatCommand);

        if(result.isExceptional()) {
            return new ChatResult(result.getExceptionType(), result.getExceptionMessage());
        }

        return (ChatResult) result;
    }
}
