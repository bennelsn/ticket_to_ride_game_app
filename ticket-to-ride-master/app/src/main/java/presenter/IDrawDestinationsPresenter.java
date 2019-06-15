package presenter;

import java.util.ArrayList;
import java.util.List;

import shared.model.DestCard;
import shared.model.DestCardSet;

/**
 * Interface for this presenter
 * Created by BenNelson on 3/6/18.
 */

public interface IDrawDestinationsPresenter {

    List<DestCard> drawDestinationCards();
    List<DestCard> getStarterDestinationCards();
    void discardDestCards(List<DestCard> chosenDestList, DestCardSet removeDestCardSet);
}
