package nonrel_database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.IModelDAO;
import shared.command.ICommand;
import shared.model.Chat;
import shared.model.Player;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by paulinecausse on 4/9/18.
 */

public class NonRelDB implements IModelDAO {
    private File playerFile;
    private File gameFile;
    private File commandFile;
    private File chatFile;
    private int commandLimit;

    public static void main(String[] args) {
        System.out.println("Built");
    }

    @Override
    public void initializeDB(int commandLimit){
        try{
            playerFile = new File("PlayerFile.txt");
            if(!playerFile.exists()){
                playerFile.createNewFile();
            }
            gameFile = new File("GameFile.txt");
            if(!gameFile.exists()){
                gameFile.createNewFile();
            }
            commandFile = new File("CommandFile.txt");
            if(!commandFile.exists()){
                commandFile.createNewFile();
            }
            chatFile = new File("ChatFile.txt");
            if(!chatFile.exists()){
                chatFile.createNewFile();
            }

            this.commandLimit = commandLimit;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void storeCommand(ICommand command){
        CommandDAO.getInstance(commandFile).addCommand(command);
    }

    @Override
    public boolean isCommandLimitReached(){
        int limit = CommandDAO.getInstance(commandFile).getCommands().size();
        if(limit > commandLimit){
            return true;
        }

        return false;
    }

    @Override
    public void saveGames(List<IGame> games){
        GameDAO.getInstance(gameFile).deleteGames();
        for(IGame game:games){
            GameDAO.getInstance(gameFile).addGame(game);
        }
    }

    @Override
    public void savePlayers(List<IPlayer> players){
        PlayerDAO.getInstance(playerFile).deletePlayers();
        for(IPlayer player:players){
            PlayerDAO.getInstance(playerFile).addPlayer(player);
        }
    }

    @Override
    public void addPlayer(IPlayer player){
        PlayerDAO.getInstance(playerFile).addPlayer(player);
    };

    @Override
    public List<IGame> getGames(){
        return GameDAO.getInstance(gameFile).getGames();
    }

    @Override
    public List<IPlayer>getPlayers(){
        return PlayerDAO.getInstance(playerFile).getPlayers();
    }

    @Override
    public void clearCommands() {

        CommandDAO.getInstance(commandFile).deleteCommands();
    }

    @Override
    public List<ICommand> getCommandList(int id){
        List<ICommand> commands = CommandDAO.getInstance(commandFile).getCommands();
        List<ICommand> wantedCommands = new ArrayList<>();
        for(ICommand command: commands){
            if(command.getGameId() == id){
                wantedCommands.add(command);
            }
        }
        return wantedCommands;
    }

    @Override
    public void saveChats(List<List<Chat>> chats){
        ChatDao.getInstance(chatFile).deleteChats();
        for(List<Chat> chat:chats){
            ChatDao.getInstance(chatFile).addChat(chat);
        }

    }

    @Override
    public List<List<Chat>> getChats(){
        return ChatDao.getInstance(chatFile).getChats();
    }

    @Override
    public void clearDatabase() {
        //GOT TO DO THIS STILL
        CommandDAO.getInstance(commandFile).deleteCommands();
        ChatDao.getInstance(chatFile).deleteChats();
        GameDAO.getInstance(gameFile).deleteGames();
        PlayerDAO.getInstance(playerFile).deletePlayers();
    }
}
