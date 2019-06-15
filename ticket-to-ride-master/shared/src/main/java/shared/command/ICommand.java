package shared.command;

import shared.results.Result;

/**
 * Created by Ben on 2/6/2018.
 */

public interface ICommand {
    public abstract Result execute() throws Exception;
    public abstract int getGameId();

    void setGameId(int id);
}
