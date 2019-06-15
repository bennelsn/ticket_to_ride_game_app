package shared.model.interfaces;

import java.util.List;
import java.util.Map;

import shared.model.TrainCard;
import shared.model.history.GameHistory;

/**
 * Created by bdemann on 3/4/18.
 */

public interface IGameInfo {
    Map<String, Integer> getPlayerDestCount();

    String getPlayerWithLongestRoute();
    List<TrainCard> getFaceUpCards();
    int getGameId();
    String getGameName();
    List<String> getPlayers();
    Map<String, Integer> getPlayerColors();
    Map<String, Integer> getPlayerPoints();
    Map<String, Integer> getPlayerHandSizes();
    void setPlayerHandSizes(String username, Integer trainCardCount);
    Map<String, List<IRoute>> getClaimedRoutes();
    void setClaimedRoutes (Map<String, List<IRoute>> claimedRoutesMap);
    Map<String, Integer> getRemainingTrains();
    void setRemainingTrains(String username, Integer trainsCount);
    GameHistory getGameHistory();
    int getTurnIndex();
    String activePlayer();
}
