package view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.a340team.tickettoride.R;

import java.util.ArrayList;

import model.ClientRoot;
import presenter.GameLobbyPresenter;

public class GameLobbyActivity extends AppCompatActivity implements IGameLobbyActivity{

    private RecyclerView _recycler;
    private EditText _chatText;
    private ImageButton _sendButton;
    private Button _startGameButton;
    private TextView _playerList;
    private String _gameID;
    ArrayList<String> player_names;

    private GameLobbyPresenter _gameLobbyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);

        //Setup _gameLobbyPresenter
        _gameLobbyPresenter = new GameLobbyPresenter(this);

        _gameID = getIntent().getStringExtra("GameID");

        _recycler = (RecyclerView) findViewById(R.id.chat_recycler);
        _chatText = (EditText) findViewById(R.id.chat_text);
        _sendButton = (ImageButton) findViewById(R.id.send_button);
        _startGameButton = (Button) findViewById(R.id.start_game_button);
        _playerList = (TextView) findViewById(R.id.current_players);

        _makeOnClickListeners();

        _gameLobbyPresenter.listGame();
        _gameLobbyPresenter.listPlayers();


    }

    @Override
    public void _makeOnClickListeners(){
        _startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_gameLobbyPresenter.checkNumPlayers()) {

                   startGame();
                }
                else{

                    ViewUtilities.displayMessage("Not Enough Players.", view.getContext());
                }
            }
        });
    }

    @Override
    public void updateGameName(String name){
        setTitle(name);
    }

    @Override
    public void updatePlayerList(String players) {
        //This gets called by the _gameLobbyPresenter. It is called with a string listing the players
        //separated by commas "Player One, Player Two, Player Three...."
        _playerList.setText(players);
        _playerList.invalidate();
    }

    @Override
    public void onBackPressed(){
        String message = _gameLobbyPresenter.leaveGame();
        ViewUtilities.displayMessage(message,this);
    }

    @Override
    public void startGame() {
        String message = _gameLobbyPresenter.startGame();
        ViewUtilities.displayMessage(message,this);
    }

    public void goToGameActivity(boolean game_start){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(ViewUtilities.GAME_START, game_start);
        startActivity(intent);
    }
}
