package nonrel_database;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shared.comm.CommandEncoder;
import shared.model.Chat;

/**
 * Created by paulinecausse on 4/17/18.
 */

public class ChatDao {
    private File file;
    private static ChatDao instance;

    public static ChatDao getInstance(File file) {
        if (instance == null)
            instance = new ChatDao(file);
        return instance;
    }

    private ChatDao(File file){
        try{
            this.file = file;
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean addChat(List<Chat> chat){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
            //deserialize player
            String strChat = CommandEncoder.encodeDBInfo(chat);
            pw.append(strChat + "end of chat");
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<List<Chat>> getChats(){
        List<List<Chat>> allChats = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("end of chat");
            while(scanner.hasNext()){
                List<Chat> chats = (List<Chat>) CommandEncoder.decodeDBInfo(scanner.next());
                allChats.add(chats);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return allChats;
    }


    public void deleteChats(){
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
