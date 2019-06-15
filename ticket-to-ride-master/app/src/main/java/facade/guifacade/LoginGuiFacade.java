package facade.guifacade;

import model.ClientRoot;
import poller.Poller;
import proxies.GameMenuServerProxy;
import proxies.LoginServerProxy;
import shared.results.Result;
import shared.results.GameListResult;
import shared.model.Player;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class LoginGuiFacade {

    public static String signIn(String username, String password) {
        //This GuiFacade will send the username and password on to the server.
        //It will receive certain results and decide what to do with them.
        LoginServerProxy lsp = new LoginServerProxy();
        Result result = lsp.signin(username,password);
        System.out.println("SignIn result: " + result.getUserMessage());
        return _processResults(username,password,result, true);
    }

    public static String register(String username, String password){

        LoginServerProxy lsp = new LoginServerProxy();
        Result result = lsp.register(username,password);
        System.out.println("Register Result: " + result.getUserMessage());
        return _processResults(username,password,result, false);
    }

    private static String _processResults(String username, String password, Result results, boolean isSignIn){

        System.out.println("Exception? " + results.getExceptionType());
        if(results == null){
            return "Server Down";
        }
        //send username and password to root
        if(results.getCommandSuccess() && isSignIn){
            _updatePlayer(username, password);
            Poller.start();
        }
        else{
            //check for exception
            if(!results.getExceptionMessage().equals("")){
                return "Exception of type: " + results.getExceptionType() +
                        ". " + results.getExceptionMessage();
            }

            if(!results.getCommandSuccess()){
                return results.getUserMessage();
            }
        }

        return results.getUserMessage();
    }

    private static void _updatePlayer(String username, String password){
        Player player = new Player(username, password);
        ClientRoot.setClientPlayer(player);
    }
    public static String getGamesList(String username) {
        GameMenuServerProxy proxy = new GameMenuServerProxy();
        Result result = proxy.getGamesList(username);
        return _processGetGamesListResult(result);
    }

    private static String _processGetGamesListResult(Result results){

        if(results == null){
            return "Server Down";
        }
        //send username and password to root
        if(results.getCommandSuccess()){
            GameListResult gameListResult = (GameListResult) results;
            ClientRoot.setListGames(gameListResult.getGameList());
        }
        else{
            //check for exception
            if(results.getExceptionType() != null){
                return "Exception of type: " + results.getExceptionType() +
                        ". " + results.getExceptionMessage();
            }

            if(!results.getCommandSuccess()){
                return results.getUserMessage();
            }
        }

        return results.getUserMessage();
    }
}

