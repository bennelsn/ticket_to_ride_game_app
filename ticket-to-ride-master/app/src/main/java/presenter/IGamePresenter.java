package presenter;

import java.util.List;
import java.util.Map;

import shared.model.Color;
import shared.model.Route;
import shared.model.TrainCardSet;
import shared.model.interfaces.IRoute;

/**
 * This interface is for the main game presenter
 * Created by BenNelson on 3/6/18.
 */

public interface IGamePresenter {

    String claimRoute(List<String> startEndCities, TrainCardSet cards);

    Map<Color,List<IRoute>> getRoutesMapForDrawing();

    boolean checkTurn();

    boolean userHasCards(Color color, int cardCount);

}
