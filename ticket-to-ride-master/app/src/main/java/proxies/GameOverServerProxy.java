package proxies;

import model.ClientRoot;
import shared.command.Command;
import shared.command.ICommand;
import shared.facades.server.IGameOverServerFacade;
import shared.model.interfaces.IGame;
import shared.results.GameOverResult;
import shared.results.Result;
import tasks.TaskExecutor;


/**
 * Created by paulinecausse on 3/24/18.
 */

public class GameOverServerProxy implements IGameOverServerFacade {
    private ICommand generateGameOverServerFacadeCommand(String method, Class<?>[] parmTypes, Object[] parmValues){
        return new Command("server.facades.GameOverServerFacade", method, parmTypes, parmValues);
    }

    public GameOverResult getTotalPoints() {
        IGame game = ClientRoot.getClientGame();
        Class<?>[] parmTypes = {IGame.class};
        Object[] parmValues = {game};

        ICommand command = generateGameOverServerFacadeCommand("getTotalPoints", parmTypes, parmValues);
        command.setGameId(ClientRoot.getClientGame().getId());

        Result result = TaskExecutor.runTask(command);

        if(result.isExceptional()){
            return new GameOverResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (GameOverResult) result;
    }
}
