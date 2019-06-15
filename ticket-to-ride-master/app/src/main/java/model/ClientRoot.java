package model;


import shared.model.Chat;
import shared.model.EndGameTotals;
import shared.model.GameInfo;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by BenNelson on 2/2/18.
 *
 */

public class ClientRoot extends Observable {

    private IPlayer _clientPlayer;
    private IGame _clientGame;
    private List<IGame> _gamesList;
    private static ClientRoot _instance = new ClientRoot();
    private IGameInfo _gameInfo;
    private List<Chat> messages;
    private static List<EndGameTotals> endGameTotals;
    private boolean isSeverDown;
    private boolean serverDownActivityUp;

    private ClientRoot(){
        this._clientPlayer = null;
        this._clientGame = null;
        this._gameInfo = new GameInfo();
        this._gamesList = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.isSeverDown = false;
    }

    public static List<Chat> getChats(){
        return _instance.messages;
    }
    //Getters and Setters
    public static IPlayer getClientPlayer(){
        return _instance._clientPlayer;
    }

    public static IGame getClientGame() {
        return _instance._clientGame;
    }

    public static IGameInfo getClientGameInfo() {
        return _instance._gameInfo;
    }

    public static List<IGame> getListGames() {
        return _instance._gamesList;
    }

    public static void setClientPlayer(IPlayer player) {
        _instance._clientPlayer = player;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static void setClientGame(IGame clientGame) {
        _instance._clientGame = clientGame;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static void setListGames(List<IGame> list) {
        if (_instance._incomingListIsDifferent(list)) {
            _instance._gamesList = list;
            _instance.setChanged();
            _instance.notifyObservers();
        }
    }

    public static void addToGameList(IGame game){
        _instance._gamesList.add(game);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static IGame getGame(int gameId){
        for(IGame game : _instance._gamesList){
            if(game.getId() == gameId){
                return game;
            }
        }
        return null;
    }

    private boolean _incomingListIsDifferent(List<IGame> incomingList) {


        if (incomingList == null && _instance._gamesList != null) {
            return true;
        }
        if (incomingList != null && _instance._gamesList == null) {
            return true;
        }
        if (incomingList == null) {
            return false;
        }
        if (incomingList.size() != _instance._gamesList.size()) {
            return true;
        }

        int count = incomingList.size();
        for (int i = 0; i < count; i++) {

            if (incomingList.get(i).getId() != _instance._gamesList.get(i).getId()) {
                return true;
            }
        }

        return false;
    }

    public static void setClientGameInfo(IGameInfo gameInfo) {
        _instance._gameInfo = gameInfo;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static void addClientRootObserver(Observer o){
        _instance.addObserver(o);
    }

    public static void addChatMessage(Chat message) {
        _instance.messages.add(message);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static void setChats(List<Chat> chats){
        _instance.messages = chats;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static List<EndGameTotals> getEndGameTotals() {
        return endGameTotals;
    }

    public static void setEndGameTotals(List<EndGameTotals> endGameTotals) {
        _instance.endGameTotals = endGameTotals;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static void removeClientRootObserver(Observer o){
        _instance.deleteObserver(o);
    }

    public static void removeAllObservers() {
        _instance.deleteObservers();
    }

    public static void somethingChanged() {
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static boolean isServerDown() {
        return _instance.isSeverDown;
    }

    public static void setServerDown(boolean isSeverDown) {
        if(_instance.isSeverDown == isSeverDown){
            return;
        }
        _instance.isSeverDown = isSeverDown;
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public static boolean isServerDownActivityUp() {
        return _instance.serverDownActivityUp;
    }

    public static void setServerDownActivityUp(boolean serverDownActivityUp) {
        _instance.serverDownActivityUp = serverDownActivityUp;
    }
}
