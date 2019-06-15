package nonrel_database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ICommandDAO;
import shared.comm.CommandEncoder;
import shared.command.ICommand;
import shared.model.interfaces.IPlayer;

/**
 * Created by paulinecausse on 4/10/18.
 */

public class CommandDAO implements ICommandDAO {
    private File file;
    private int numCommands;
    private static CommandDAO instance;

    public static CommandDAO getInstance(File file) {
        if (instance == null)
            instance = new CommandDAO(file);
        return instance;
    }

    private CommandDAO(File file){
        try{
            this.file = file;
            numCommands = 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getCommandLimit(){
        return numCommands;
    }


    public boolean addCommand(ICommand command){
        try{
            //deserialize player
            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
            String strCommand = CommandEncoder.encodeDBInfo(command);
            pw.append(strCommand + "end of command");
            pw.close();
            numCommands ++;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ICommand> getCommands(){
        List<ICommand> commands = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("end of command");
            while(scanner.hasNext()){
                ICommand command = (ICommand) CommandEncoder.decodeDBInfo(scanner.next());
                commands.add(command);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return commands;
    }

    public void deleteCommands(){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(file,false));
            pw.append("");
            pw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        numCommands = 0;
    }
}
