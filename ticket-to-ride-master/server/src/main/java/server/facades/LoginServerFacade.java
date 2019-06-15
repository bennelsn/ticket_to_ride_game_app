package server.facades;

import server.database.Database;
import server.model.ServerRoot;
import server.poller.ClientCommands;
import shared.results.LoginResult;
import shared.results.RegisterResult;
import shared.logging.Level;
import shared.logging.Logger;
import shared.model.interfaces.IPlayer;
import shared.model.Player;
import shared.facades.server.ILoginServerFacade;

/**
 *
 * Created by Ben on 2/6/2018.
 */

public class LoginServerFacade implements ILoginServerFacade {

    @Override
    public LoginResult signin(String username, String password) {
        Logger.log("Logging in user: " + username, Level.FINNEST);

        IPlayer player = ServerRoot.getPlayer(username);
        if(player == null){
            Logger.log("User " + username + " does not exist.", Level.FINNEST);
            return new LoginResult(false, ClientCommands.getCommandList(username), "Player does not exists");
        }
        if(player.getPassword().equals(password)){
            Logger.log("Login Successful", Level.FINNEST);
            return new LoginResult(true, ClientCommands.getCommandList(username), "Login successful");
        }
        Logger.log("Login failed", Level.FINNEST);
        return new LoginResult(false, ClientCommands.getCommandList(username), "Wrong password");

    }

    @Override
    public RegisterResult register(String username, String password) {
        IPlayer player = ServerRoot.getPlayer(username);
        if(player != null){
            // Player already exists
            return new RegisterResult(false, ClientCommands.getCommandList(username), "Player already exists");
        }
        player = new Player(username, password);
        ServerRoot.addPlayer(player);
        Database.getModelDAO().addPlayer(player);
        return new RegisterResult(true, ClientCommands.getCommandList(username),"Register successful");
    }
}
