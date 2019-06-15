package shared.facades.server;

import shared.model.DestCardSet;
import shared.model.TrainCardSet;
import shared.model.interfaces.IRoute;
import shared.results.ClaimRouteResult;
import shared.results.DrawDestCardsResult;
import shared.results.DrawTrainCardsResult;

/**
 * Created by bdemann on 2/28/18.
 */

public interface IGameServerFacade {

    public ClaimRouteResult claimRoute(IRoute route, TrainCardSet cards, String username);

    public DrawDestCardsResult drawDestCards(String username);
    public DrawDestCardsResult discardDestCards(String username, DestCardSet keptCards, DestCardSet discardCards);

    public DrawTrainCardsResult drawFaceUpTrainCard(String username, int trainCardIndex);
    public DrawTrainCardsResult drawFaceDownTrainCard(String username);

}
