package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.model.history.GameHistory;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IRoute;

/**
 *
 * Created by bdemann on 3/4/18.
 */

public class GameInfo implements IGameInfo, Serializable {
    private String _playerWithLongestRoute;
    private List<TrainCard> _faceUpCards;
    private int _gameId;
    private String _gameName;
    //Show information about all players in the game, including
    // their names,
    private List<String> _players; // A list of players in turn order.
    // colors,
    private Map<String, Integer> _playerColors;
    // points
    private Map<String, Integer> _playerPoints;
    // and the order in which the players take turns
    private Map<String, Integer> _playerHandSizes;
    private Map<String, Integer> _playerDestCards;
    private Map<String, List<IRoute>> _claimedRoutes;
    private Map<String, Integer> _playerRemainingTrains;
    private GameHistory _gameHistory;
    private int _turnIndex;

    public GameInfo(){
        this._playerWithLongestRoute = "";
        this._faceUpCards = new ArrayList<>();
        this._gameId = -1;
        this._gameName = "";
        this._players = new ArrayList<>();
        this._playerColors = new HashMap<>();
        this._playerPoints = new HashMap<>();
        this._playerHandSizes = new HashMap<>();
        this._claimedRoutes = new HashMap<>();
        this._playerRemainingTrains = new HashMap<>();
        this._gameHistory = new GameHistory();
        this._turnIndex = 0;
    }

    public GameInfo(int gameId, String gameName, GameHistory _gameHistory, String playerWithLongestRoute, List<TrainCard> faceUpCards, List<String> players, Map<String, Integer> playerColors, Map<String, Integer> playerPoints, Map<String, Integer> playerHandSizes, Map<String, Integer> playerDestCout, Map<String, List<IRoute>> claimedRoutes, Map<String, Integer> playerRemainingTrains, GameHistory gameHistory, int turnIndex) {
        this._playerWithLongestRoute = playerWithLongestRoute;
        this._faceUpCards = faceUpCards;
        this._gameId = gameId;
        this._gameName = gameName;
        this._players = players;
        this._playerColors = playerColors;
        this._playerPoints = playerPoints;
        this._playerHandSizes = playerHandSizes;
        this._claimedRoutes = claimedRoutes;
        this._playerRemainingTrains = playerRemainingTrains;
        this._gameHistory = gameHistory;
        this._turnIndex = turnIndex;
        this._playerDestCards = playerDestCout;
    }

    @Override
    public Map<String, Integer> getPlayerDestCount() {
        return _playerDestCards;
    }

    @Override
    public String getPlayerWithLongestRoute() {
        return _playerWithLongestRoute;
    }

    @Override
    public List<TrainCard> getFaceUpCards() {
        return _faceUpCards;
    }

    @Override
    public int getGameId() {
        return _gameId;
    }

    @Override
    public String getGameName() {
        return _gameName;
    }

    @Override
    public List<String> getPlayers() {
        return _players;
    }

    @Override
    public Map<String, Integer> getPlayerColors() {
        return _playerColors;
    }

    @Override
    public Map<String, Integer> getPlayerPoints() {
        return _playerPoints;
    }

    @Override
    public Map<String, Integer> getPlayerHandSizes() {
        return _playerHandSizes;
    }

    @Override
    public void setPlayerHandSizes(String username, Integer trainCardCount) {
        if(_playerHandSizes.containsKey(username)){
            _playerHandSizes.put(username,trainCardCount);
        }
    }

    @Override
    public Map<String, List<IRoute>> getClaimedRoutes() {
        return _claimedRoutes;
    }

    @Override
    public void setClaimedRoutes(Map<String, List<IRoute>> claimedRoutesMap) {
        this._claimedRoutes = claimedRoutesMap;
    }

    @Override
    public Map<String, Integer> getRemainingTrains() {
        return _playerRemainingTrains;
    }

    @Override
    public void setRemainingTrains(String username, Integer trainsCount) {
        if(_playerRemainingTrains.containsKey(username)){
            _playerRemainingTrains.put(username,trainsCount);
        }
    }

    @Override
    public GameHistory getGameHistory() {
        return _gameHistory;
    }

    @Override
    public int getTurnIndex() {
        return _turnIndex;
    }

    @Override
    public String activePlayer() {
        if(_players.size() <= _turnIndex) {
            System.out.println("##############We shouldnt be calling this yet!!!!!!###########33");
            return null;
        }
        return _players.get(_turnIndex);
    }
}
