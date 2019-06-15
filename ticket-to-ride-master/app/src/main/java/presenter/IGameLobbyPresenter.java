package presenter;

/**
 * Created by mikeporet on 2/12/18.
 */

public interface IGameLobbyPresenter {

    boolean checkNumPlayers();
    String leaveGame();
    String startGame();
}
