package presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenNelson on 2/12/18.
 *
 */
public interface IGameSelectionPresenter {

    boolean joinGame(int gameID);

    ArrayList<String> getGameNames();
    ArrayList<Integer> getGameIDs();
    ArrayList<String> getGameNumPlayers();
}
