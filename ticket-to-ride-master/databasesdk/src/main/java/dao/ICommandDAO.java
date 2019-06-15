package dao;

import java.util.List;

import shared.command.ICommand;

/**
 * Created by bdemann on 4/9/18.
 */

public interface ICommandDAO {
    public boolean addCommand(ICommand command);
    public int getCommandLimit();
    public List<ICommand> getCommands();
    public void deleteCommands();
}
