package shared.model.interfaces;
import java.util.List;

import shared.model.DestCard;
import shared.model.DestDeck;
import shared.model.Route;
import shared.model.TTRMap;
import shared.model.TrainCard;
import shared.model.TrainCardSet;
import shared.model.TrainDeck;
import shared.model.history.GameHistory;

/**
 * Interface for a single Game in the ticket to ride game yay!
 *
 * Created by BenNelson on 2/2/18.
 */
public interface IGame {
    void setPlayerWithLongestRoute(String playerName);
    void setTrainCardDeck(TrainDeck trainCardDeck);
    void setDestCardDeck(DestDeck destCardDeck);
    void setCardsFaceUp(List<TrainCard> cardsFaceUp);
    void setOpenRoutes(List<String> openRoutes);
//    void setPlayers(List<IPlayer> players);
    void generateScoreSummary();
    void setGameMap(TTRMap gameMap);
    String getPlayerWithLongestRoute();
    TrainDeck getTrainCardDeck();
    DestDeck getDestCardDeck();
    List<TrainCard> getCardsFaceUp();
    List<String> getOpenRoutes();
    List<IRoute> getClaimedRoutes();
    List<IPlayer> getPlayers();
    String getScoreSummary();
    TTRMap getGameMap();
    void addPlayer(IPlayer joiner);
    void setId(int gameId);
    int getId();
    int getNumberPlayer();
    int getMaxNumberPlayer();
    void setGameName(String gameName);
    String getGameName();
    void removePlayer(IPlayer player);
    IPlayer getPlayer(String username);
    boolean isGameStarted();
    void startGame();
    IGameInfo getGameInfo();
    IRoute isRouteAvailable(IRoute route);
    IRoute claimRoute(IRoute route, String owner);
    void blockRoute(IRoute route);

    GameHistory getGameHistory();
    int getTurnIndex();
    IPlayer getActivePlayer();
    int incrementTurnIndex();

    void discardTrainCards(TrainCardSet cards);
    void discardDestCards(List<DestCard> cards);

    void updatePlayerDestCard(IPlayer newPlayer,List<DestCard> destCards);
    void updatePlayerTrainCard(IPlayer newPlayer,TrainCard card);

    void discardTrainCard(TrainCard trainCard);

    void setPlayers(List<IPlayer> players);
}
