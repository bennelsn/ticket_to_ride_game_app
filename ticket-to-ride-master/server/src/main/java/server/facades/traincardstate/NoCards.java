package server.facades.traincardstate;

import server.facades.GameServerFacade;
import shared.model.TrainCard;
import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 3/30/18.
 */

class NoCards extends TrainCardState {
    public NoCards(GameServerFacade gameServerFacade) {
        super(gameServerFacade);
    }

    @Override
    public TrainCard drawFaceUpCard(IGame game, int cardIndex) {
        return null;
    }

    @Override
    public TrainCard drawFaceDownCard(IGame game) {
        return null;
    }
}
