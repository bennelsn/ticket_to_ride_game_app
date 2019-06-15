package server.comm;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.CommandHandler;
import shared.logging.Level;
import shared.logging.Logger;

/**
 *
 * Created by Ben on 2/6/2018.
 */

public class ServerCommunicator {
    private static final int MAX_WAITING_CONNECTIONS = 10;

    private HttpServer server;
    private int serverPortNumber;

    public ServerCommunicator(int serverPortNumber) {
        this.setServerPortNumber(serverPortNumber);
    }

    public void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(getServerPortNumber()), MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            Logger.log(e.getMessage(), Level.ALL);
            return;
        }

        server.setExecutor(null); // use the default executor.

        server.createContext("/sendCommand", command);

        server.start();
    }

    private HttpHandler command = new CommandHandler();

    public static void terminate() {
        System.exit(0);
    }

    public int getServerPortNumber() {
        return serverPortNumber;
    }

    public void setServerPortNumber(int serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }
}
