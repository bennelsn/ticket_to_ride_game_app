package shared.model.interfaces;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by bdemann on 3/8/18.
 */

public abstract class Message implements Serializable {

    private String username;
    private String message;
    private long time;

    public Message(String username, String message, long time){
        this.username = username;
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getFormattedTime() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, HH:mm:ss z");
        return dateFormat.format(getTime());
    }
}
