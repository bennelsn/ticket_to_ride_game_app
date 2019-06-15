package nonrel_database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.tree.ExpandVetoException;

import dao.IGameDAO;
import shared.comm.CommandEncoder;
import shared.model.interfaces.IGame;

/**
 * Created by paulinecausse on 4/9/18.
 */

public class GameDAO implements IGameDAO {
    private File file;
    private static GameDAO instance;

    public static GameDAO getInstance(File file) {
        if (instance == null)
            instance = new GameDAO(file);
        return instance;
    }

    private GameDAO(File file){
        try{
            this.file = file;
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean addGame(IGame game){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
            //deserialize player
            String strGame = CommandEncoder.encodeDBInfo(game);
            pw.append(strGame + "end of game");
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<IGame> getGames(){
        List<IGame> games = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("end of game");
            while(scanner.hasNext()){
                IGame game = (IGame) CommandEncoder.decodeDBInfo(scanner.next());
                games.add(game);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Size of list of games: " + games.size());
        return games;
    }


    @Override
    public void deleteGames(){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(file,false));
            pw.append("");
            pw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
