package shared.results;

import shared.command.ICommand;
import shared.logging.Level;
import shared.logging.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulinecausse on 2/3/18.
 */

public class Result implements Serializable {
    private boolean _isExceptional;
    protected boolean _success;
    protected List<ICommand> _clientCommands;
    protected String _userMessage;
    protected String _exceptionType;
    protected String _exceptionMessage;

    public Result(boolean success, List<ICommand> clientCommands, String userMessage){
        this._success = success;
        this._isExceptional = false;
        this._userMessage = userMessage;
        this._clientCommands = clientCommands;
        if (clientCommands == null) {
            Logger.log("The client commands were null", Level.FINNEST);
            this._clientCommands = new ArrayList<>();
        }
        this._exceptionType = "";
        this._exceptionMessage = "";
    }

    public Result(String exceptionType, String exceptionMessage){
        this._success = false;
        this._isExceptional = true;
        this._exceptionType = exceptionType;
        this._exceptionMessage = exceptionMessage;
        this._clientCommands = new ArrayList<>();
        this._userMessage = "There was an exception";
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Results:\n");
        sb.append("Success: ").append(_success).append("\n");
        for (ICommand command : _clientCommands){
            sb.append(command.toString()).append("\n");
        }
        return sb.toString();
    }

    public boolean isExceptional() {
        return _isExceptional;
    }

    public String getExceptionType()
    {
        return _exceptionType;
    }

    public String getExceptionMessage()
    {
        return _exceptionMessage;
    }

    public List<ICommand> getClientCommands() { return _clientCommands; }

    public boolean getCommandSuccess()
    {
        return _success;
    }

    public String getUserMessage()
    {
        return _userMessage;
    }

}
