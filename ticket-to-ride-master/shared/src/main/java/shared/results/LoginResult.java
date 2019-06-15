package shared.results;

import java.util.List;

import shared.command.ICommand;

/**
 * Created by Ben on 2/6/2018.
 */

public class LoginResult extends Result {
    public LoginResult(boolean success, List<ICommand> clientCommands, String userMessage){
        super(success, clientCommands, userMessage);
    }

    public LoginResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Login Result - ");
        sb.append("Success: " + _success);
        sb.append(" | ");
        sb.append("Message: " + _userMessage);
        return sb.toString();
    }
}
