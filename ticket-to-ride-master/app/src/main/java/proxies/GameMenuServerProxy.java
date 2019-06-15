package proxies;

import shared.command.Command;
import shared.command.ICommand;
import shared.facades.server.IGameMenuServerFacade;
import shared.model.interfaces.IPlayer;
import shared.results.CreateGameResult;
import shared.results.GameListResult;
import shared.results.JoinGameResult;
import shared.results.Result;
import tasks.TaskExecutor;

/**
 *
 * Created by Ben on 2/7/2018.
 */

public class GameMenuServerProxy implements IGameMenuServerFacade {
    @Override
    public CreateGameResult createGame(IPlayer creator, int numberPlayer, String gameName) {
        Class<?>[] parmTypes = {IPlayer.class, int.class, String.class};
        Object[] parmValues = {creator, numberPlayer, gameName};
        ICommand createGameCommand = new Command("server.facades.GameMenuServerFacade", "createGame", parmTypes, parmValues);

        Result result = TaskExecutor.runTask(createGameCommand);
        if(result.isExceptional()) {
            return new CreateGameResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (CreateGameResult) result;
    }

    @Override
    public JoinGameResult joinGame(int gameId, String joinerUsername) {
        Class<?>[] parmTypes = {int.class, String.class};
        Object[] parmValues = {gameId, joinerUsername};

        ICommand joinGameCommand = new Command("server.facades.GameMenuServerFacade", "joinGame", parmTypes, parmValues);

        Result result = TaskExecutor.runTask(joinGameCommand);
        if(result.isExceptional()) {
            return new JoinGameResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (JoinGameResult) result;
    }

    @Override
    public GameListResult getGamesList(String username) {
        Class<?>[] parmTypes = {String.class};
        Object[] parmValues = {username};
        ICommand getGamesCommand = new Command("server.facades.GameMenuServerFacade", "getGamesList", parmTypes, parmValues);

        Result result = TaskExecutor.runTask(getGamesCommand);
        if(result.isExceptional()) {
            return new GameListResult(result.getExceptionType(), result.getExceptionMessage());
        }
        return (GameListResult) result;
    }
}
