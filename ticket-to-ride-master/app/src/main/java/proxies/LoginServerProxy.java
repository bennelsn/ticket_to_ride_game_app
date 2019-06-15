package proxies;

import java.util.concurrent.ExecutionException;

import shared.command.Command;
import shared.command.ICommand;
import shared.results.LoginResult;
import shared.results.RegisterResult;
import shared.results.Result;
import shared.facades.server.ILoginServerFacade;
import tasks.CommandTask;
import tasks.TaskExecutor;

/**
 * Created by Ben on 2/7/2018.
 *
 */

public class LoginServerProxy implements ILoginServerFacade{
    public LoginServerProxy(){}
    @Override
    public LoginResult signin(String username, String password) {
        Result result = TaskExecutor.runTask(generateLoginCommand("signin", username, password));

        if(result.isExceptional()) {
            return new LoginResult(result.getExceptionType(), result.getExceptionMessage());
        }

        return (LoginResult) result;
    }

    @Override
    public RegisterResult register(String username, String password) {
        Result result = TaskExecutor.runTask(generateLoginCommand("register", username, password));

        if(result.isExceptional()) {
            return new RegisterResult(result.getExceptionType(), result.getExceptionMessage());
        }

        return (RegisterResult) result;

    }

    private ICommand generateLoginCommand(String methodName, String username, String password){
        Class<?>[] parmTypes = {String.class, String.class};
        String[] parm = {username, password};
        String className = "server.facades.LoginServerFacade";

        return new Command(className, methodName, parmTypes, parm);
    }
}
