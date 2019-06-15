package shared.logging;

/**
 * Created by bdemann on 2/9/18.
 */

public class Logger {

    public static final Logger _instance = new Logger();
    private Level _level;

    private Logger(){
        _level = Level.ALL;
    }

    public static void setLevel(Level level){
        _instance._level = level;
    }

    public static void log(String message, Level level){
        if (level.ordinal() <= _instance._level.ordinal()) {
            System.out.println(message);
        }
    }

    public static void log(Object message, Level level){
        if (level.ordinal() <= _instance._level.ordinal()) {
            System.out.println(message.toString());
        }
    }

    public static void log(Object message){
        log(message, Level.ALL);
    }

    public static void log(int message){
        log(new Integer(message), Level.ALL);
    }
}
