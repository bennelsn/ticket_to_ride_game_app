package server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import shared.model.Chat;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 *
 * Created by Ben on 2/6/2018.
 */

public class ServerRoot extends Observable {
    private List<IPlayer> _players;
    private List<IGame> _games;
    private List<List<Chat>> _chats;
    private int gameId = 0;
    private List<Integer> _possibleColor;

    private static final ServerRoot _instance = new ServerRoot();
    private String _pluginPath;
    private int _commandListLen;
    private boolean _hasPlugin;

    private ServerRoot() {
        super();
        _players = new ArrayList<>();
        _games = new ArrayList<>();
        _chats = new ArrayList<>();
        _possibleColor = new ArrayList<Integer>();
        _possibleColor.add(-65536); //RED
        _possibleColor.add(-16776961);  //BLUE
        _possibleColor.add(-256);   //YELLOW
        _possibleColor.add(-16711936);  //GREEN
        _possibleColor.add(-16777216);  //BLACK
        _pluginPath = "";

    }

    public static void addGame(IGame game) {
        _instance._addGame(game);
    }

    public static void addPlayer(IPlayer player) {
        _instance._addPlayer(player);
    }

    public static void setChats(List<List<Chat>> chats) {
        _instance._chats = chats;
    }

    private void _addGame(IGame game){
        game.setId(gameId);
        gameId++;
        _games.add(game);

        _instance._chats.add(game.getId(), new ArrayList<Chat>());
    }

    private void _addPlayer(IPlayer player){
        _players.add(player);
    }


    public static IGame getGame(int gameId) {
        return _instance._games.get(gameId);
    }

    public static void addChat(IGame game, Chat message) {
        _instance._chats.get(game.getId()).add(message);
    }

    public static IPlayer getPlayer(String username) {
        for (IPlayer player : _instance._players) {
            if (player.getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }

    public static List<IPlayer> getPlayers(){
        return _instance._players;
    }

    public static List<IGame> getGames() {
        return _instance._games;
    }

    public static List<Integer> getColors() {
        return _instance._possibleColor;
    }

    public static List<Chat> getChats(int id) {
        return _instance._chats.get(id);
    }

    public static String getPluginPath() {
        return _instance._getPluginPath();
    }

    private String _getPluginPath() {
        return _pluginPath;
    }

    public static void setPluginPath(String pluginPath) {
        _instance._setPluginPath(pluginPath);
    }

    private void _setPluginPath(String pluginPath) {
        this._pluginPath = pluginPath;
    }

    public static void setCommandListLen(int commandListLen) {
        _instance._setCommandListLen(commandListLen);
    }

    private void _setCommandListLen(int commandListLen) {
        this._commandListLen = commandListLen;
    }

    public static boolean hasPlugin() {
        return _instance._hasPlugin;
    }

    public static void setHasPlugin(boolean hasPlugin) {
        _instance._hasPlugin = hasPlugin;
	}

    public static void setGames(List<IGame> games){
        _instance._games = games;
        for(int i = 0; i < games.size(); i++) {
            if(_instance._chats.size() <= i) {
                _instance._chats.add(new ArrayList<Chat>());
            }
        }
    }

    public static void setPlayers(List<IPlayer> players){
        _instance._players = players;
	}

    public static List<List<Chat>> getChats() {
        return _instance._chats;
    }
}
