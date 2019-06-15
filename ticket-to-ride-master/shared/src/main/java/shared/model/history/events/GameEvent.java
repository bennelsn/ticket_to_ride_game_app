package shared.model.history.events;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import shared.model.interfaces.Message;

/**
 * Created by bdemann on 3/4/18.
 */

public class GameEvent extends Message implements Serializable {
    public GameEvent(String username, String event, long time) {
        super(username, event, time);
    }
}
