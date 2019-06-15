package dao;

import java.util.List;

import shared.command.ICommand;
import shared.model.Chat;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by bdemann on 4/9/18.
 *
 */

public interface IModelDAO {
    void initializeDB(int commandLimit);
    void storeCommand(ICommand command);
    boolean isCommandLimitReached();
    void saveGames(List<IGame>games);
    void savePlayers(List<IPlayer>players);
    List<IGame> getGames();
    void clearCommands();
    void addPlayer(IPlayer player);
    List<IPlayer> getPlayers();
    List<ICommand> getCommandList(int id);
    void saveChats(List<List<Chat>> chats);
    List<List<Chat>> getChats();

    void clearDatabase();
}
