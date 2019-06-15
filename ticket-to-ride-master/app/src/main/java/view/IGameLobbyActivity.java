package view;

/**
 * Created by mikeporet on 2/10/18.
 */

public interface IGameLobbyActivity {

    void _makeOnClickListeners();
    void updateGameName(String name);
    void updatePlayerList(String players);
    void startGame();
}
