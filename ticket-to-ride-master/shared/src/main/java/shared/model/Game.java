package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.SchemaOutputResolver;

import shared.logging.Logger;
import shared.model.history.GameHistory;
import shared.model.initialized_info.Routes;
import shared.model.interfaces.IRoute;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

/**
 *
 * Created by BenNelson on 2/2/18.
 */
public class Game implements IGame, Serializable {

    private List<IPlayer> _players;
    private int _id;
    private int _maxNumberPlayer;
    private int _numberPlayer;
    private String _gameName;
    private boolean _gameIsStarted = false;
    private String _playerWithLongestRoute; //If no one has it it will be empty string.
    private TrainDeck _trainDeck;
    private DestDeck _destDeck;
    private List<TrainCard> _faceUpCards;
    private GameHistory _gameHistory;
    private int _turnIndex;
    private List<String> _openRoutes;
    private List<IRoute> _claimedRoutes;

    public Game(String gameName, List<IPlayer> players, int maxNumberPlayer){
        this._players = players;
        this._maxNumberPlayer = maxNumberPlayer;
        this._numberPlayer = 1;
        this._gameName = gameName;
        this._playerWithLongestRoute = "";
        this._trainDeck = new TrainDeck();
        this._destDeck = new DestDeck();
        this._faceUpCards = new ArrayList<>();
        this._gameHistory = new GameHistory();
        this._openRoutes = Routes.instance().getRoutesList();
        this._claimedRoutes = new ArrayList<>();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("Game: ").append(_gameName).append(". Game id: ").append(_id).append("\n").toString();
    }

    public void setId(int id){
        this._id = id;
    }

    @Override
    public void setPlayerWithLongestRoute(String playerName) {
        _playerWithLongestRoute = playerName;
    }

    @Override
    public void setTrainCardDeck(TrainDeck trainCardDeck) {
        _trainDeck = trainCardDeck;
    }

    @Override
    public void setDestCardDeck(DestDeck destCardDeck) {
        _destDeck = destCardDeck;
    }

    @Override
    public void setCardsFaceUp(List<TrainCard> cardsFaceUp) {
        _faceUpCards = cardsFaceUp;
    }

    @Override
    public void setOpenRoutes(List<String> openRoutes) {
        this._openRoutes = openRoutes;
    }


//    @Override
//    public void setPlayers(List<IPlayer> players) {
//        this._players = players;
//
//    }

    @Override
    public void generateScoreSummary() {
        //TODO implement this method
    }

    @Override
    public void setGameMap(TTRMap gameMap) {
        //TODO implement this method
    }

    public void setGameName(String gameName){
        this._gameName = gameName;
    }

    @Override
    public String getPlayerWithLongestRoute() {
        return _playerWithLongestRoute;
    }

    @Override
    public TrainDeck getTrainCardDeck() {
        return _trainDeck;
    }

    @Override
    public DestDeck getDestCardDeck() {
        return _destDeck;
    }

    @Override
    public List<TrainCard> getCardsFaceUp() {
        return _faceUpCards;
    }

    @Override
    public List<String> getOpenRoutes() {
        return this._openRoutes;
    }

    @Override
    public List<IRoute> getClaimedRoutes() {
        return this._claimedRoutes;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return this._players;
    }

    @Override
    public String getScoreSummary() {
        //TODO implement this method
        return null;
    }

    @Override
    public TTRMap getGameMap() {
        //TODO implement this method
        return null;
    }

    @Override
    public void addPlayer(IPlayer joiner) {
        _players.add(joiner);
        _numberPlayer ++;
    }

    public int getId() {
        return _id;
    }

    public int getMaxNumberPlayer() {
        return _maxNumberPlayer;
    }

    public int getNumberPlayer() {
        return _numberPlayer;
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
    public IPlayer getActivePlayer() {
        return _players.get(_turnIndex);
    }

    @Override
    public int incrementTurnIndex() {
        Logger.log("We are incrementing the turn.");
        //If not every player has had a turn
        if(++_turnIndex < _players.size()){
            // Move turn to the next player
            return _turnIndex;
        }
        // Otherwise start the turns over again.
        _turnIndex = 0;
        return _turnIndex;
    }

    @Override
    public void discardTrainCards(TrainCardSet cards) {
        _trainDeck.discard(cards.getTrainCards());
    }

    @Override
    public void discardTrainCard(TrainCard trainCard) {
        _trainDeck.discard(trainCard);
    }

    @Override
    public void setPlayers(List<IPlayer> players) {
        this._players = players;
    }

    @Override
    public void discardDestCards(List<DestCard> cards) {
        _destDeck.discard(cards);
    }

    @Override
    public String getGameName(){
        return _gameName;
    }

    @Override
    public void removePlayer(IPlayer player) {
        boolean removed = false;
        for(IPlayer listPlayer : new ArrayList<>(_players)){
            if(listPlayer.getUsername().equals(player.getUsername())){
                _players.remove(listPlayer);
                removed = true;
            }
        }
        if(removed) {
            _numberPlayer--;
        }

    }

    @Override
    public IPlayer getPlayer(String username) {
        for(IPlayer player : _players) {
            if (player.getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean isGameStarted() {
        return _gameIsStarted;
    }

    @Override
    public void startGame() {
        _gameIsStarted = true;
    }

    @Override
    public IGameInfo getGameInfo() {
        List<String> players = new ArrayList<>();
        Map<String, Integer> playerColors = new HashMap<>();
        Map<String, Integer> playerHandSizes = new HashMap<>();
        Map<String, Integer> playerPoints = new HashMap<>();
        Map<String, Integer> trainsRemaining = new HashMap<>();
        Map<String, List<IRoute>> claimedRoutes = new HashMap<>();
        Map<String, Integer> playerDestCount = new HashMap<>();
        for(IPlayer player : _players) {
            String username = player.getUsername();
            players.add(username);
            playerColors.put(username, player.getColor());
            playerHandSizes.put(username, player.getTrainCardHand().size());
            playerPoints.put(username, player.getScore());
            trainsRemaining.put(username, player.getTrains().size());
            List<IRoute> playerRoutes = new ArrayList<>();
            for(IRoute route : _claimedRoutes) {
                if(route.getOwner().equals(player.getUsername())) {
                    playerRoutes.add(route);
                }
            }
            claimedRoutes.put(username, playerRoutes);
            playerDestCount.put(username, player.getDestCards().size());
        }
        return new GameInfo(_id, _gameName, _gameHistory, _playerWithLongestRoute, _faceUpCards, players, playerColors, playerPoints, playerHandSizes, playerDestCount, claimedRoutes, trainsRemaining, getGameHistory(), _turnIndex);
    }

    @Override
    public IRoute isRouteAvailable(IRoute route) {

        String start = route.getStart().get_name();
        String end = route.getEnd().get_name();
        Map<String, IRoute> map = Routes.instance().getRoutesMap();
        for(String routeName : _openRoutes){
            if(map.containsKey(routeName)){
                IRoute currentRoute = map.get(routeName);
                String s = currentRoute.getStart().get_name();
                String e = currentRoute.getEnd().get_name();

                if((start.equals(s) && end.equals(e)) || (start.equals(e) && end.equals(s))){
                    //Then it is an open route
                    return new Route(currentRoute.getLength(),currentRoute.getStart(),currentRoute.getEnd(),currentRoute.getColor(),false);
                }
            }

        }

        return null;
    }

    @Override
    public IRoute claimRoute(IRoute route, String owner) {
        //This assumes the incoming route is valid
        //We need to use this route to tell the Game that it is now claimed
        String start = route.getStart().get_name();
        String end = route.getEnd().get_name();
        //Find it in the map of Routes
        Map<String, IRoute> map = Routes.instance().getRoutesMap();

        String routeToClaim = "";
        for(String routeName: map.keySet()){
            IRoute currentRoute = map.get(routeName);
            //I'm okay with this message chaining
            if(currentRoute.getStart().get_name().equals(start) &&    currentRoute.getEnd().get_name().equals(end) &&    currentRoute.getColor().equals(route.getColor()) && _coordsAreEqual(currentRoute, route)){
                //We found the route, so now we just need it's string value, which is routeName
                routeToClaim = routeName;
                break;
            }
        }
        //Remove it from the open routes
        _openRoutes.remove(routeToClaim);
        //Add it to the claimed routes
        route.setOwner(owner);
        _claimedRoutes.add(route);
        return route;
    }

    @Override
    public void blockRoute(IRoute route){

        String start = route.getStart().get_name();
        String end = route.getEnd().get_name();
        //Find it in the map of Routes
        Map<String, IRoute> map = Routes.instance().getRoutesMap();

        String routeToBlock = null;
        for(String routeName: _openRoutes) {
            if(map.containsKey(routeName)) {
                IRoute r = map.get(routeName);
                if(r.getStart().get_name().equals(start) && r.getEnd().get_name().equals(end) && _coordsAreEqual(r, route)){
                    //Found the route to block
                    routeToBlock = routeName;
                    break;
                }
            }
        }
        if(routeToBlock != null){
            _openRoutes.remove(routeToBlock);
        }
    }

    private boolean _coordsAreEqual(IRoute r1, IRoute r2) {

        CityPoint r1Start = r1.getStart().get_coordinates();
        CityPoint r1End = r1.getEnd().get_coordinates();
        CityPoint r2Start = r2.getStart().get_coordinates();
        CityPoint r2End = r2.getEnd().get_coordinates();

        if((r1Start.x() == r2Start.x()) && (r1Start.y() == r2Start.y()) && (r1End.x() == r2End.x()) && (r1End.y() == r2End.y())){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updatePlayerDestCard(IPlayer newPlayer,List<DestCard> destCards){
        for(IPlayer player: _players){
            if(newPlayer.getUsername().equals(player.getUsername())){
                player.addDestCards(destCards);
            }
        }
    }

    @Override
    public void updatePlayerTrainCard(IPlayer newPlayer,TrainCard card){
        for(IPlayer player: _players){
            if(newPlayer.getUsername().equals(player.getUsername())){
                player.addTrainCard(card);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return _id == game._id;

    }

    @Override
    public int hashCode() {
        return _id;
    }
}
