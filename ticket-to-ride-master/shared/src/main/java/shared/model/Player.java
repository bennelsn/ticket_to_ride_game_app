package shared.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.model.interfaces.IPlayer;

/**
 * This class implements the player class for ticket to ride.
 *
 * Created by BenNelson on 2/2/18.
 */
public class Player implements IPlayer, Serializable {

    private String _username;
    private String _password;
    private int _color;
    private int _gameId = -1;
    private int _points = 0;
    private List<Route> playersRoutes;
    private List<Train> _trains;
    private Hand<TrainCard> _trainCards;
    private Hand<DestCard> _destCards;
    private List<DestCard> _unresolvedDestCards;

    public Player(String username, String password, int color)
    {
        this._username = username;
        this._password = password;
        this._color = color;
        this.playersRoutes = new ArrayList<>();
        this._trains = new ArrayList<>();
        this._destCards = new Hand<>(new ArrayList<DestCard>());
        this._trainCards = new Hand<>(new ArrayList<TrainCard>());
        this._unresolvedDestCards = new ArrayList<>();
    }

    public Player(String username, String password) {
        this._username = username;
        this._password = password;
        this._color = 0;
        this.playersRoutes = new ArrayList<>();
        this._trains = new ArrayList<>();
        this._destCards = new Hand<>(new ArrayList<DestCard>());
        this._trainCards = new Hand<>(new ArrayList<TrainCard>());
        this._unresolvedDestCards = new ArrayList<>();
    }

    public Player(String username) {
        this._username = username;
        this._password = "";
        this._color = 0;
        this.playersRoutes = new ArrayList<>();
        this._trains = new ArrayList<>();
        this._destCards = new Hand<>(new ArrayList<DestCard>());
        this._trainCards = new Hand<>(new ArrayList<TrainCard>());
        this._unresolvedDestCards = new ArrayList<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(this._username).append(". Color: ").append(this._color);
        return sb.toString();
    }

    @Override
    public void setUsername(String username) {
        this._username = username;
    }

    @Override
    public void setPassword(String password) {
        this._password = password;
    }

    public void setGameId(int id){
        this._gameId = id;
    }

    @Override
    public void setTrainCards(List<TrainCard> startingHand) {
        _trainCards = new Hand<>(startingHand);
    }

    @Override
    public void setUnresolvedDestCards(List<DestCard> unresolvedDestCards) {
        _unresolvedDestCards = unresolvedDestCards;
    }

    @Override
    public DestCardSet getUnresolvedDestCards() {
        return new DestCardSet(_unresolvedDestCards);
    }

    @Override
    public List<DestCard> getDestCards() {
        return this._destCards.get_cards();
    }

    @Override
    public void setDestCards(List<DestCard> destCards) {
        this._destCards = new Hand<>(destCards);
    }

    @Override
    public void addDestCards(List<DestCard> newDestCards) {
        this._destCards.addCards(newDestCards);
    }

    @Override
    public Hand<TrainCard> getTrainCardHand() {
        return _trainCards;
    }

    @Override
    public void incrementScore(int score) {
        this._points += score;
    }

    @Override
    public void decrementTrains(int length) {
        for(int i = 0; i < length; i++) {
            _trains.remove(0);
        }
    }

    @Override
    public void setTrainCards(Hand<TrainCard> hand) {
        this._trainCards = hand;
    }

    @Override
    public void incrementRouteCount() {
        //TODO we could do something like routes.size() if we were using the routes.
    }

    @Override
    public String getUsername() {
        return this._username;
    }

    @Override
    public String getPassword() {
        return this._password;
    }

    @Override
    public void addDestCard(DestCard card) {

    }

    @Override
    public void discardDestCard(DestCard card) {

    }

    @Override
    public void addTrainCard(TrainCard card) {
        _trainCards.addCard(card);
    }

    @Override
    public void discardTrainCard(TrainCard card) {
        _trainCards.useCard(card);
        //TODO this will get it out of the players hand but it won't put it into the discard pile... maybe we need to return the discarded card so that some other class can take care of that?
    }

    @Override
    public void addRoute(Route route) {

    }

    @Override
    public void performAction(PlayerAction action) {

    }

    @Override
    public void setTrains(List<Train> trains) {
        this._trains = trains;
    }

    @Override
    public void setColor(int color) {
        this._color = color;

    }

    @Override
    public void setScore(int score) {
        _points = score;
    }

    @Override
    public void setScoreMarker(int scoreMarker) {

    }

    @Override
    public void setCurrentGame(int currentGame) {

    }

    @Override
    public List<Train> getTrains() {
        return _trains;
    }

    @Override
    public int getColor() {
        return this._color;
    }

    @Override
    public int getScore() {
        return _points;
    }

    @Override
    public int getScoreMarker() {
        return 0;
    }

    @Override
    public int getCurrentGame() {
        return _gameId;
    }

    @Override
    public int getRemainingActions() {
        return 0;
    }

    public int getGameId(){
        return _gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return _username.equals(player._username);

    }

    @Override
    public int hashCode() {
        return _username.hashCode();
    }
}
