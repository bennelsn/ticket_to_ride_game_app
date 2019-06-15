package nonrel_database;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.IPlayerDAO;
import shared.comm.CommandEncoder;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;

/**
 * Created by paulinecausse on 4/9/18.
 */

public class PlayerDAO implements IPlayerDAO {
    private File file;

    private static PlayerDAO instance;
    public static PlayerDAO getInstance(File file) {
        if (instance == null)
            instance = new PlayerDAO(file);
        return instance;
    }

    private PlayerDAO(File file){
        try{
            this.file = file;
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean addPlayer(IPlayer player){
        try{
            //deserialize player
            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
            String strPlayer = CommandEncoder.encodeDBInfo(player);
            pw.append(strPlayer + "end of player");
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<IPlayer> getPlayers(){
        System.out.println("GETTING PLAYERS");
        List<IPlayer> players = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("end of player");
            while(scanner.hasNext()){
//                String test = scanner.next();
//                if(test.contains("end of player")){
//                    System.out.println("IT HAS END OF PLAYER");
//                }
                IPlayer player = (IPlayer) CommandEncoder.decodeDBInfo(scanner.next());
                players.add(player);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Size of player: " + players.size());
        return players;
    }

    @Override
    public void deletePlayers(){
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
