package server.poller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shared.command.Command;
import shared.command.ICommand;
import shared.logging.Level;
import shared.logging.Logger;

/**
 * Created by bdemann on 2/12/18.
 */

public class ClientCommands {

    private static ClientCommands _instance = new ClientCommands();
    private Map<String, List<ICommand>> _commands;

    private ClientCommands() {
        _commands = new HashMap<>();
    }

    public static Set<String> getUsers() {
        return _instance._commands.keySet();
    }

    public static List<ICommand> getCommandList(String username) {
        List<ICommand> commands = _instance._commands.get(username);
        _instance._commands.put(username, new ArrayList<ICommand>());
        return commands;
    }

    public static void addCommand(String username, ICommand command) {
        List<ICommand> commands;
        if(_instance._commands.containsKey(username)) {
            commands = _instance._commands.get(username);
        } else {
            commands = new ArrayList<>();
        }
        commands.add(command);
        Logger.log("We are adding the command " + command + " to " + username + "'s command list", Level.SANITY_CHECK);
        _instance._commands.put(username, commands);
    }
}
