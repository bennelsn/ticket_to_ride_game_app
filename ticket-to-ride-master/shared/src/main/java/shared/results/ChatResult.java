package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.Chat;

/**
 * Created by Ben on 2/6/2018.
 */

public class ChatResult extends Result {

    private List<Chat> _message;

    public ChatResult(List<Chat> sentMessage, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this._message = sentMessage;
    }

    public ChatResult(String errorType, String errorMessage) {
        super(errorType, errorMessage);
    }

    public List<Chat> getMessage(){
        return _message;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chat Result - ");
        sb.append("Success: " + _success);
        sb.append(" | ");
        sb.append("Message Sent: " + _message);
        return sb.toString();
    }
}
