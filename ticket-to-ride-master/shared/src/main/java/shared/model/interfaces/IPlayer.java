package shared.model.interfaces;


import java.util.List;

import shared.model.DestCardSet;
import shared.model.DestCard;
import shared.model.Hand;
import shared.model.PlayerAction;
import shared.model.Route;
import shared.model.Train;
import shared.model.TrainCard;

/**
 * This is the interface for a player in ticket to ride
 *
 * Created by BenNelson on 2/2/18.
 */
public interface IPlayer {

    /**
     *
     * @param username of the player signed in
     */
    void setUsername(String username);

    /**
     *
     * @param password of the player signed in
     */
    void setPassword(String password);

    /**
     *
     * @return the username as a string
     */
    String getUsername();

    /**
     *
     * @return the password as s string
     */
    String getPassword();

    /**
     *
     * @param card
     * return nothing.
     */
    void addDestCard(DestCard card);

    /**
     * Discards a destination card
     * @param card
     * return nothing
     */
    void discardDestCard(DestCard card);

    /**
     * This method adds a train card to the player's current traincard hand.
     * There is no limit of train _cards a player can have.
     *
     * @param card
     * return nothing
     */
    void addTrainCard(TrainCard card);

    /**
     * Discards a train card
     * @param card
     * return nothing
     */
    void discardTrainCard(TrainCard card);

    /**
     * Adds a route to the players route list
     * @param route
     * return nothing
     */
    void addRoute(Route route);

    /**
     * This takes in an action the player wants to perform and deducts it from the amount of actions
     * a player can take during his or her turn
     * @param action
     * return nothing
     */
    void performAction(PlayerAction action);

    /**
     * Set the trains for the player
     * @param trains
     * return nothing
     */
    void setTrains(List<Train> trains);

    /**
     * Set the color of the player
     * @param color for the player
     */
    void setColor(int color);

    /**
     * A player has a given score
     * @param score for player
     */
    void setScore(int score);

    /**
     * Each player has a score maker that ranges from 1 to 100
     * @param scoreMarker for the player
     */
    void setScoreMarker(int scoreMarker);

    /**
     * This is an ID number for the current game the player is in.
     * @param currentGame of the player
     */
    void setCurrentGame(int currentGame);

    /**
     * Players have trains
     * @return list of trains
     */
    List<Train> getTrains();

    /**
     * Each player has a color
     * @return Color of the player
     */
    int getColor();

    /**
     *
     * @return score of the player
     */
    int getScore();

    /**
     *
     * @return the int location of the score marker.
     */
    int getScoreMarker();

    /**
     *
     * @return the id number of the current game the player is in.
     */
    int getCurrentGame();

    /**
     *
     * @return the number of actions the player has until his or her turn is over.
     */
    int getRemainingActions();


    int getGameId();

    void setGameId(int id);

    void setTrainCards(List<TrainCard> startingHand);

    /**
     * This is where the destination cards live while the player is picking which ones to keep and which ones to get rid of.
     * @param startingTickets
     */
    void setUnresolvedDestCards(List<DestCard> startingTickets);
    DestCardSet getUnresolvedDestCards();

    List<DestCard> getDestCards();

    void setDestCards(List<DestCard> destCards);

    void addDestCards(List<DestCard> newDestCards);

    Hand<TrainCard> getTrainCardHand();

    void incrementScore(int score);

    void decrementTrains(int length);

    void setTrainCards(Hand<TrainCard> hand);

    void incrementRouteCount();
}
