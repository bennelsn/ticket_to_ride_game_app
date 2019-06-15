package presenter;

import java.util.List;

import shared.model.DestCard;
import shared.model.TrainCard;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.ITrainCard;

/**
 * Created by paulinecausse on 3/7/18.
 */

public interface IGameInfoPresenter {
    IGameInfo getStarterGameInfo();
    List<TrainCard> getStarterPlayerHand();
    List<DestCard> getDestinationCards();
    List<Boolean> getCompleteDestinations();
}
