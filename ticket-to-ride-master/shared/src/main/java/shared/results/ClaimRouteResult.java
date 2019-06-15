package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.Hand;
import shared.model.Route;
import shared.model.Train;
import shared.model.TrainCard;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IRoute;

/**
 * Created by bdemann on 2/28/18.
 *
 */

public class ClaimRouteResult extends Result {

    Hand<TrainCard> hand;
    IGameInfo gameInfo;

    public ClaimRouteResult(boolean success, Hand<TrainCard> trainCardHand, IGameInfo gameInfo, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this.hand = trainCardHand;
        this.gameInfo = gameInfo;
    }

    public ClaimRouteResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public Hand<TrainCard> getHand() {
        return hand;
    }

    public IGameInfo getGameInfo() {
        return gameInfo;
    }
}
