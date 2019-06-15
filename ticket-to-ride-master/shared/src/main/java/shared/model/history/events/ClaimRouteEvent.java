package shared.model.history.events;

import java.io.Serializable;

import shared.model.interfaces.IRoute;

/**
 * Created by bdemann on 3/4/18.
 */

public class ClaimRouteEvent extends GameEvent implements Serializable {
    public ClaimRouteEvent(String username, IRoute claimedRoute, long time) {
        super(username, "claimed " + claimedRoute.toString(), time);
    }
}
