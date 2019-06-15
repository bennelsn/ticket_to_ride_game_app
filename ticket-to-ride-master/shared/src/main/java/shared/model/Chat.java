package shared.model;

import java.io.Serializable;

import shared.model.interfaces.IPlayer;
import shared.model.interfaces.Message;

/**
 * Created by Ben on 2/6/2018.
 */

public class Chat extends Message implements Serializable {
    private IPlayer speaker;

    public Chat(IPlayer speaker, String message, long time) {
        super(speaker.getUsername(), message, time);
        this.speaker = speaker;
    }

    public IPlayer getSpeaker() {
        return speaker;
    }

}
