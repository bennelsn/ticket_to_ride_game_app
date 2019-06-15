package shared.model.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.logging.Level;
import shared.logging.Logger;
import shared.model.history.events.GameEvent;

/**
 * Created by bdemann on 2/28/18.
 */

public class GameHistory implements Serializable{
    private List<GameEvent> history;

    public GameHistory(){
        history = new ArrayList<>();
    }

    public void addEvent(GameEvent event){
        history.add(event);
        Logger.log(event.getUsername() + " " + event.getMessage() + " at " + event.getFormattedTime(), Level.FINE);
    }

    public List<GameEvent> getEvents(){
        return history;
    }
}
