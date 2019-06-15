package server.facades.traincardstate;

import server.facades.GameServerFacade;
import shared.model.TrainCard;
import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 3/24/18.
 */

public abstract class TrainCardState {

    protected GameServerFacade gameServerFacade;

    public TrainCardState(GameServerFacade gameServerFacade){
        this.gameServerFacade = gameServerFacade;
    }

    public abstract TrainCard drawFaceUpCard(IGame game, int cardIndex);
    public abstract TrainCard drawFaceDownCard(IGame game);

}
