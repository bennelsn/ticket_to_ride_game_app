package comm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import shared.command.ICommand;
import shared.comm.CommandEncoder;
import shared.logging.Level;
import shared.logging.Logger;
import shared.results.Result;

/**
 *
 *
 *
 * Created by paulinecausse on 2/3/18.
 */

public class ClientCommunicator {

    private static final String SERVER_HOST = "192.168.2.191";
    private static final String SERVER_PORT = "8080";
    private static String URL_PREFIX = "http://" + SERVER_HOST + ":" + SERVER_PORT + "/";
    private static final String HTTP_POST = "POST";

    private static ClientCommunicator _clientCommunicator = new ClientCommunicator();

    public static Result sendCommand(ICommand command){
        return _clientCommunicator._sendCommand(command);
    }

    public static void setServerURL(String serverHost){
        URL_PREFIX = "http://" + serverHost + ":" + SERVER_PORT + "/";
    }

    //Command
    private Result _sendCommand(ICommand command) {
        try {
            return doPost("sendCommand", command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.log("DO POST FAILED", Level.ALL);
        return null;
    }

    private Result doPost(String urlPath, ICommand postData) throws Exception {
        try {
            URL url = new URL(URL_PREFIX + urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setDoOutput(true);
            connection.connect();
            CommandEncoder.encodeCommand(postData, connection.getOutputStream());
            connection.getOutputStream().close();


            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return CommandEncoder.decodeCommandResults(connection.getInputStream());

            } else {
                throw new Exception(
                        "doPost failed: " + urlPath + " (http code " + connection.getResponseCode() + ")");
            }
        } catch (IOException e) {
            System.out.println("Throwing exception");
            throw e;
        }
    }

}
