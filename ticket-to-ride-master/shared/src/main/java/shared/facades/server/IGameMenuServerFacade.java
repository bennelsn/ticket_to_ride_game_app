package shared.facades.server;

import shared.results.CreateGameResult;
import shared.results.GameListResult;
import shared.results.JoinGameResult;
import shared.results.Result;
import shared.model.interfaces.IPlayer;

/**
 * Created by Ben on 2/6/2018.
 */

public interface IGameMenuServerFacade {
    /**
     * Creates a game on the server
     *
     * @pre The creator is an IPlayer that is logged into the server.
     * @pre 2 <= numberPlayer <= 5
     * @pre gameName is not the name of a game that already exits on the server
     *
     * @post A new game will be on the server.
     * @post That game will allow the number of players specified.
     * @post Creator will be a player in that game.
     * @post The game will have the specified name.
     *
     * @param creator is the user that is creating the game
     * @param numberPlayer is the number of players that will be in the game
     * @param gameName is the name of the game.
     * @return A Result class with info about the created game.
     */
    public CreateGameResult createGame(IPlayer creator, int numberPlayer, String gameName);

    /**
     * Adds the IPlayer joiner to the game with gameId
     *
     * @pre there is a game on the server where id = gameId
     * @pre id <= 0
     * @pre The joiner is an IPlayer that is logged into the server.
     *
     * @post Joiner will be a player in the game with the specified id.
     *
     * @param gameId the id of the game the joiner wants to join
     * @param joinerUsername the IPlayer that is joining the game
     * @return A Result class with info about the joined game.
     */
    public JoinGameResult joinGame(int gameId, String joinerUsername);

    /**
     * Returns a list of games on the server.
     *
     * @pre There is a player logged in on the server where player.username = username
     *
     * @param username
     * @return A Result class with a list games on the server
     */
    public GameListResult getGamesList(String username);
}
