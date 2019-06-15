package facade.guifacade;

import model.ClientRoot;
import proxies.GameMenuServerProxy;
import shared.results.CreateGameResult;
import shared.results.JoinGameResult;
import shared.results.Result;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 *
 * Created by BenNelson on 2/3/18.
 */

public class CreateGameGuiFacade {

    public static String createGame(int maxNumberPlayer, int color, String gameName) {

        GameMenuServerProxy proxy = new GameMenuServerProxy();
        ClientRoot.getClientPlayer().setColor(color);
        Result result = proxy.createGame(ClientRoot.getClientPlayer(),maxNumberPlayer,gameName);
        IGame newGame = _processResults(result);

        if(newGame != null){
            CreateGameResult createGameResult = (CreateGameResult) result;
            //Get the player and gameID for the player to join
            String player = ClientRoot.getClientPlayer().getUsername();
            int gameId = newGame.getId();

            //Join the game
            Result joinResults = proxy.joinGame(gameId,player);
            return _processJoinResults(joinResults, createGameResult.getUserMessage());
        }
        else{
            return "Failed to create game";
        }
    }

    private static IGame _processResults(Result results)
    {
        if(results == null){
            return null;
        }

        if(results.getCommandSuccess()){
            CreateGameResult createGameResult = (CreateGameResult) results;

            //add the game to the root.
            _addGame(createGameResult.getGame());
            return createGameResult.getGame();
        }
        else{
            return null;
        }
    }

    private static String _processJoinResults(Result results, String gameCreatedMessage){
        if(results == null){
            return "Created game but failed to join it";
        }
        if(results.getCommandSuccess()){

            JoinGameResult joinGameResult = (JoinGameResult) results;

            if(joinGameResult.getGame() == null){
                return "Join result is null for some reason";
            }
            //game joined
            _setJoinedGame(joinGameResult.getGame());

            //
            _setPlayersGameId(joinGameResult.getGame());
            //

        }
        else{
            return "Created game but failed to join it";
        }
        return gameCreatedMessage;
    }


    //
    private static void _setPlayersGameId(IGame game){
        ClientRoot.getClientPlayer().setGameId(game.getId());
    }
    //


    private static void _addGame(IGame game){
        ClientRoot.addToGameList(game);
    }

    private static void _setJoinedGame(IGame game){
        ClientRoot.setClientGame(game);
    }
}
