package server.facades;

import java.util.ArrayList;
import java.util.List;

import server.database.Database;
import server.poller.ClientNotifications;
import server.model.ServerRoot;
import server.poller.ClientCommands;
import shared.model.history.events.GameEvent;
import shared.results.CreateGameResult;
import shared.results.JoinGameResult;
import shared.results.GameListResult;
import shared.facades.server.IGameMenuServerFacade;
import shared.logging.Level;
import shared.logging.Logger;
import shared.model.Game;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by Ben on 2/6/2018.
 */

public class GameMenuServerFacade implements IGameMenuServerFacade {
    @Override
    public CreateGameResult createGame(IPlayer creator, int maxNumberPlayer, String gameName) {
//        IPlayer creator = ServerRoot.getPlayer(creatorUsername);
        if(creator == null){
            Logger.log("creator is null", Level.ALL);
        }
        else{
            Logger.log("creator is not null", Level.ALL);
        }
        Logger.log("Creating game: " + gameName + ". Creator: " + creator.toString() + " ", Level.FINNEST);
        List<IPlayer> players = ServerRoot.getPlayers();
        if(players == null){
            Logger.log("There are no players in the Server Root", Level.ALL);
        }
        else{
            Logger.log("There are " + Integer.toString(players.size()) + " players in the Server Root", Level.ALL);
        }
        IPlayer player = ServerRoot.getPlayer(creator.getUsername());

        if(player == null) {
            Logger.log("player couldn't be found", Level.ALL);
        }

        //set color to creator
        ServerRoot.getPlayer(creator.getUsername()).setColor(creator.getColor());



        //Create a list of players for the game.
        List<IPlayer> playerList = new ArrayList<>();
        playerList.add(player);

        //Add game to server
        IGame game = new Game(gameName, playerList, maxNumberPlayer);
        ServerRoot.addGame(game);
        player.setGameId(game.getId());

        Logger.log("Game: " + game, Level.FINNEST);



        game.getGameHistory().addEvent(new GameEvent(creator.getUsername(), "created the game", System.currentTimeMillis()));
        ClientNotifications.gameCreated(game.getId(), player.getUsername());

        Database.getModelDAO().saveGames(ServerRoot.getGames());
        CreateGameResult createGameCommandResult = new CreateGameResult(game, true, ClientCommands.getCommandList(creator.getUsername()));

        Logger.log("Game Creation Successful! Results:" + createGameCommandResult.toString(), Level.FINNEST);
        Logger.log("CommandResult: " + createGameCommandResult.getGame(), Level.FINNEST);

        return createGameCommandResult;
    }

    @Override
    public JoinGameResult joinGame(int gameId, String joinerUsername) {
        IPlayer joiner = ServerRoot.getPlayer(joinerUsername);
        Logger.log("Joining game: " + gameId + "Joining player: " + joiner, Level.FINNEST);
        IGame currentGame = ServerRoot.getGame(gameId);
        if(currentGame == null){
            Logger.log("Couldn't find the current game", Level.ALL);
            return new JoinGameResult(currentGame, false, ClientCommands.getCommandList(joiner.getUsername()),"Could not find game");
        }
        else if (currentGame.getPlayers().contains(joiner)){
            Logger.log(ServerRoot.getGame(currentGame.getId()).isGameStarted());
            Logger.log(joinerUsername + " is re-joining the game", Level.ALL);
            currentGame.getGameHistory().addEvent(new GameEvent(joiner.getUsername(), "joined the game", System.currentTimeMillis()));
            JoinGameResult jgresult = new JoinGameResult(ServerRoot.getGame(currentGame.getId()), true, ClientCommands.getCommandList(joiner.getUsername()),"Join successful");
            return jgresult;
        }
        else if(currentGame.getNumberPlayer() >= currentGame.getMaxNumberPlayer()){
            Logger.log("Already has the max number of players.", Level.ALL);
            return new JoinGameResult(currentGame, false, ClientCommands.getCommandList(joiner.getUsername()),"Cannot join. Game is full");
        }

        joiner.setGameId(currentGame.getId());

        //check color
        _assignColor(currentGame.getId(), joiner);
        //add the player if not already in
        _updatePlayerList(currentGame, joiner);

        currentGame.getGameHistory().addEvent(new GameEvent(joiner.getUsername(), "joined the game", System.currentTimeMillis()));
        ClientNotifications.playerJoinedGame(gameId, joiner.getUsername());

        JoinGameResult results = new JoinGameResult(ServerRoot.getGame(currentGame.getId()), true, ClientCommands.getCommandList(joiner.getUsername()),"Join successful");

        Logger.log("Join Game successful " + results.toString(), Level.FINNEST);
        return results;
    }

    private void _assignColor(int gameId, IPlayer joiner){
        if(joiner.getColor() != 0){
            return;
        }
        List<IPlayer> players = ServerRoot.getGame(gameId).getPlayers();
        for(int i = 0; i < ServerRoot.getColors().size(); i++){
            int notSame = 0;
            for(int j = 0; j < players.size(); j++){
                if(ServerRoot.getColors().get(i) != players.get(j).getColor()){
                    notSame++;
                }
            }
            if(notSame == players.size()){
                ServerRoot.getPlayer(joiner.getUsername()).setColor(ServerRoot.getColors().get(i));
                joiner.setColor(ServerRoot.getColors().get(i));
                break;
            }
        }
    }

    private void _updatePlayerList(IGame currentGame, IPlayer joiner){
        boolean playerExists =  false;
        for(IPlayer player : ServerRoot.getGame(currentGame.getId()).getPlayers()){
            if(player.getUsername().equals(joiner.getUsername())){
                playerExists = true;
            }
        }
        if(!playerExists){
            ServerRoot.getGame(currentGame.getId()).addPlayer(joiner);
            ServerRoot.getPlayer(joiner.getUsername()).setGameId(currentGame.getId());
        }
        else{
            ServerRoot.getGame(currentGame.getId()).getPlayer(joiner.getUsername()).setGameId(currentGame.getId());
            ServerRoot.getPlayer(joiner.getUsername()).setGameId(currentGame.getId());
        }
    }

    @Override
    public GameListResult getGamesList(String username) {
        Logger.log("Getting the games list", Level.FINNEST);
        for (IGame game: ServerRoot.getGames()) {
            Logger.log("Games: " + game.toString(), Level.FINNEST);
        }
        Logger.log("Finished getting the game list", Level.FINNEST);
        return new GameListResult(ServerRoot.getGames(), true, ClientCommands.getCommandList(username));
    }

}
