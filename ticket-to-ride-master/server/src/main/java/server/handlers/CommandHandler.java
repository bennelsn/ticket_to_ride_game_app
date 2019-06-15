package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

import dao.IModelDAO;
import server.database.Database;
import server.model.ServerRoot;
import server.pluginmanager.PluginManager;
import shared.comm.CommandEncoder;
import shared.command.ICommand;
import shared.model.interfaces.IPlayer;
import shared.results.CreateGameResult;
import shared.results.Result;
import shared.logging.Level;
import shared.logging.Logger;

/**
 * Created by Ben on 2/6/2018.
 */

public class CommandHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Logger.log("We are starting the handler", Level.SANITY_CHECK);
        ICommand command = null;
        Result results = null;
        try {
            command = (ICommand) CommandEncoder.decodeCommand(exchange.getRequestBody());
        } catch (Exception e){
            results = new Result(e.getClass().toString(), e.getMessage());
            e.printStackTrace();
        }


        try {
            results = command.execute();
            Logger.log(results, Level.FINNEST);
        } catch (Exception e){
            results = new Result(e.getClass().toString(), e.getMessage());
            e.printStackTrace();
        }


        if(ServerRoot.hasPlugin()) {
            IModelDAO modelDAO = Database.getModelDAO();
            modelDAO.savePlayers(ServerRoot.getPlayers());
            if(command.getGameId() >= 0) {
                modelDAO.storeCommand(command);
                if (modelDAO.isCommandLimitReached()) {
                    Logger.log("We are CLEARING COMMANDS");
                    modelDAO.clearCommands();
                    modelDAO.saveChats(ServerRoot.getChats());
                    modelDAO.saveGames(ServerRoot.getGames());
                }
            }
        }

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        CommandEncoder.encodeCommandResults(results, exchange.getResponseBody());
        exchange.getResponseBody().close();
    }
}
