package proxies;

import java.util.concurrent.ExecutionException;

import shared.command.Command;
import shared.logging.Level;
import shared.results.Result;
import shared.logging.Logger;
import shared.model.interfaces.IGame;
import shared.facades.server.ILobbyServerFacade;
import shared.results.StartGameResult;
import tasks.CommandTask;
import tasks.TaskExecutor;

/**
 * Created by Ben on 2/7/2018.
 *
 */

public class LobbyServerProxy implements ILobbyServerFacade {

    private static final String CLASS = "server.facades.LobbyServerFacade";

    @Override
    public StartGameResult startGame(IGame game, String username) {

        Logger.log("We are starting the game.", Level.FINNEST);
        Class<?>[] parmTypes = {IGame.class, String.class};
        Object[] parmValues = {game, username};
        Command startGameCommand = new Command(CLASS, "startGame", parmTypes, parmValues);

        Logger.log("This is the startGame command: " + startGameCommand, Level.SANITY_CHECK);

        Result result = TaskExecutor.runTask(startGameCommand);
        if(result.isExceptional()){
            return new StartGameResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (StartGameResult) result;
    }

    @Override
    public Result leaveGame(String username) {
        Logger.log("We are leaving the game", Level.FINNEST);
        Class<?>[] parmTypes = {String.class};
        Object[] parmValues = {username};
        Command leaveCommand = new Command(CLASS, "leaveGame", parmTypes, parmValues);

        Logger.log("This is the leave command: " + leaveCommand, Level.SANITY_CHECK);

        return TaskExecutor.runTask(leaveCommand);
    }
}
