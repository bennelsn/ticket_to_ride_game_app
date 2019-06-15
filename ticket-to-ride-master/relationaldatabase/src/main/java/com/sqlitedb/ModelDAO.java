package com.sqlitedb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dao.IModelDAO;
import shared.command.Command;
import shared.command.ICommand;
import shared.model.Chat;
import shared.model.Game;
import shared.model.Player;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

import static com.sqlitedb.RelationalDatabase.*;


public class ModelDAO implements IModelDAO {


    private RelationalDatabase db;
    private int climit;

    public static void main(String[] args){
        ModelDAO modelDAO = new ModelDAO();
        int commandLimit = 18;
        modelDAO.initializeDB(commandLimit);
        modelDAO.clearDatabase();


        /*
        //TEST STORE COMMAND
        int commandsStored = 10;
        for(int i = 0; i < commandsStored; i++) {
            String index = Integer.toString(i);
            modelDAO.storeCommand(new Command("className" + index, "methodName" + index));
        }
        System.out.printf("Store Command Test: SUCCESS\n");
        //END OF STORING COMMANDS


        //TEST COMMAND LIMIT IS REACHED
        if(modelDAO.isCommandLimitReached()){
            System.out.printf("Command Limit was reached with COMMANDS STORED: " + Integer.toString(commandsStored) + "   and COMMAND LIMIT SET TO: " + Integer.toString(commandLimit) + "\n");
        }
        //END OF TESTING COMMAND LIMIT

        //TEST ADDING PLAYERS
        IPlayer one = new Player("Ben Nelson", "Yo Mama", 0);
        IPlayer two = new Player("Bob Trythall", "Hey Yo", 1);
        IPlayer three = new Player("Garet Nelson", "27348", 2);
        IPlayer four = new Player("Joe Frank", "Excel", 3);
        modelDAO.addPlayer(one);
        modelDAO.addPlayer(two);
        modelDAO.addPlayer(three);
        modelDAO.addPlayer(four);
        //END OF ADDING PLAYERS

        //TESTING GET PLAYERS
        for(IPlayer player : modelDAO.getPlayers()){
            System.out.printf("Got Player: " + player.getUsername() + "\n");
        }
        //END OF GET PLAYERS TEST

        //CLEAR COMMANDS WORKS

        //TESTING GET COMMANDS
        for(ICommand command : modelDAO.getCommandList(-1)){
            System.out.printf("Got COMMAND FROM GAME ID: " + command.getGameId() + "\n");
        }
        //END OF GET COMMANDS TEST

        //TESTING SAVE GAMES
        List<IGame> games = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            IGame game = new Game("Game" + Integer.toString(i), modelDAO.getPlayers(), 2);
            game.setId(i);
            games.add(game);
        }
        modelDAO.saveGames(games);*/
        //END OF TESTING SAVE GAMES

        //TESTING GET GAMES
        for(IGame game : modelDAO.getGames()){
            System.out.printf("Got Game: " + game.getGameName() + "\n");
            for(IPlayer player : game.getPlayers()){
                System.out.printf("\tContains Player: " + player.getUsername() + "\n");
            }
        }
        /*
        //END OF GET GAMES TEST

        //TESTING SAVE PLAYERS
        IPlayer five = new Player("Jacob Nixon", "Yo Mama", 0);
        IPlayer six = new Player("Ted Blight", "Hey Yo", 1);
        List<IPlayer> test = new ArrayList<>();
        test.add(five);
        test.add(six);
        modelDAO.savePlayers(test);

        System.out.printf("\n\nTESTING SAVE PLAYERS...\n\nHere's the new Player List:\n\n");
        */

        for(IPlayer player : modelDAO.getPlayers()){
            System.out.println("We Got Player: " + player.getUsername());
        }
        //END OF TESTING SAVE PLAYERS
        //donzo






    }

    //TESTED and should work properly
    @Override
    public void initializeDB(int commandLimit) {
        this.db = new RelationalDatabase();
        this.climit = commandLimit;
        File file = new File("ticketToRide.sqlite");
        if(!file.exists()) {
            try {
                db.openConnection();
                db.createTables();
                //Add the command limit for the database

                List<java.lang.Object> list = new ArrayList<>();
                list.add(commandLimit);
                db.insert(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT, list);

                //Close the db connection
                db.closeConnection(true);
                System.out.println("Database Initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("I guess it existed");
            //Switch the command limit
            if(db != null){
                try {
                    db.openConnection();
                    db.clearTable(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT);
                    List<java.lang.Object> list = new ArrayList<>();
                    list.add(commandLimit);
                    db.insert(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT, list);
                    db.closeConnection(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //TESTED and should work properly
    @Override
    public void storeCommand(ICommand command) {
        if(db != null){

            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Object> list = new ArrayList<>();
            list.add(command);
            try {
                db.openConnection();
                db.insert(TABLE_COMMANDS, COLUMN_COMMANDS, list);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TESTED and should work properly
    @Override
    public boolean isCommandLimitReached() {
        if(db != null){

            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                db.openConnection();
                int limit = (int) db.load(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT).get(0);
                int commandListSize = (int) db.load(TABLE_COMMANDS, COLUMN_COMMANDS).size();
                db.closeConnection(true);

                if(commandListSize >= limit){
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    //TESTED and should work properly
    @Override
    public void saveGames(List<IGame> games) {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Object> list = new ArrayList<>();
            list.addAll(games);
            try {
                db.openConnection();
                db.clearTable(TABLE_GAMES, COLUMN_GAME_BLOB);
                db.insert(TABLE_GAMES, COLUMN_GAME_BLOB, list);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TESTED and should work properly
    @Override
    public void savePlayers(List<IPlayer> players) {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Object> list = new ArrayList<>();
            list.addAll(players);
            try {
                db.openConnection();
                db.clearTable(TABLE_PLAYERS, COLUMN_PLAYER_BLOB);
                db.insert(TABLE_PLAYERS, COLUMN_PLAYER_BLOB, list);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TESTED and should work properly
    @Override
    public void addPlayer(IPlayer player) {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Object> list = new ArrayList<>();
            list.add(player);
            try {
                db.openConnection();
                db.insert(TABLE_PLAYERS, COLUMN_PLAYER_BLOB, list);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TESTED and should work properly
    @Override
    public List<IGame> getGames() {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                db.openConnection();
                List<Object> list = db.load(TABLE_GAMES, COLUMN_GAME_BLOB);
                db.closeConnection(true);

                List<IGame> games = new ArrayList<>();
                for(Object obj : list){
                    if(obj instanceof IGame){
                        games.add((IGame) obj);
                        System.out.println("We are adding this game: " + obj);
                    }
                }
                System.out.println("This is the num of games we are returning: " + games.size());
                return games;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    //TESTED and should work properly
    @Override
    public List<IPlayer> getPlayers() {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                db.openConnection();
                List<Object> list = db.load(TABLE_PLAYERS, COLUMN_PLAYER_BLOB);
                db.closeConnection(true);

                List<IPlayer> players = new ArrayList<>();
                for(Object obj : list){
                    if(obj instanceof IPlayer){
                        players.add((IPlayer) obj);
                    }
                }
                return players;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    //TESTED and should work properly
    @Override
    public void clearCommands() {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                db.openConnection();
                db.clearTable(TABLE_COMMANDS, COLUMN_COMMANDS);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TESTED and should work properly
    @Override
    public List<ICommand> getCommandList(int id){
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                db.openConnection();
                List<Object> list = db.load(TABLE_COMMANDS, COLUMN_COMMANDS);
                db.closeConnection(true);

                List<ICommand> commands = new ArrayList<>();
                for(Object obj : list){
                    if(obj instanceof ICommand){
                        ICommand cmd = (ICommand) obj;
                        if(cmd.getGameId() == id){
                            commands.add(cmd);
                        }
                    }
                }
                return commands;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void saveChats(List<List<Chat>> chats) {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<Object> list = new ArrayList<>();
            list.addAll(chats);
            try {
                db.openConnection();
                db.clearTable(TABLE_CHATS, COLUMN_CHATS);
                db.insert(TABLE_CHATS, COLUMN_CHATS, list);
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<List<Chat>> getChats() {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                db.openConnection();
                List<Object> list = db.load(TABLE_CHATS, COLUMN_CHATS);
                db.closeConnection(true);

                List<List<Chat>> chats = new ArrayList<>();

                for(Object obj : list){
                    chats.add((List<Chat>) obj);
                }
                return chats;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void clearDatabase() {
        if(db != null){
            try {
                ensureAccess();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                db.openConnection();
                db.clearTable(TABLE_GAMES, COLUMN_GAME_BLOB);
                db.clearTable(TABLE_COMMANDS, COLUMN_COMMANDS);
                db.clearTable(TABLE_PLAYERS, COLUMN_PLAYER_BLOB);
                db.clearTable(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT);
                db.clearTable(TABLE_CHATS, COLUMN_CHATS);

                List<java.lang.Object> list = new ArrayList<>();
                list.add(this.climit);
                db.insert(TABLE_COMMAND_LIMIT, COLUMN_COMMAND_LIMIT, list);

                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void ensureAccess() throws Exception {
        if(db !=null) {
            db.checkConnection();
        }
    }
}