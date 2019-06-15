package view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.a340team.tickettoride.R;

import model.ClientRoot;
import presenter.CreateJoinPresenter;

public class CreateGameActivity extends AppCompatActivity implements IGameSelection{

    private EditText GameNameField;
    private ImageButton redButton;
    private ImageButton blueButton;
    private ImageButton yellowButton;
    private ImageButton greenButton;
    private ImageButton blackButton;
    private Button PlayersTwo;
    private Button PlayersThree;
    private Button PlayersFour;
    private Button PlayersFive;
    private Button CreateGameButton;
    private Button CancelButton;
    private String GameName;
    private int PlayerColor;
    private int NumberOfPlayers;
    private CreateJoinPresenter _createJoinPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        _initializGuiElements();
        _enableAllButtons();
        _onClickListenerSetup();
        _setUpObserver();

    }

    private void _setUpObserver(){
            _createJoinPresenter = new CreateJoinPresenter(this);
            ClientRoot.addClientRootObserver(_createJoinPresenter);

    }


    private void _enableAllButtons(){
        redButton.setEnabled(true);
        blueButton.setEnabled(true);
        yellowButton.setEnabled(true);
        greenButton.setEnabled(true);
        blackButton.setEnabled(true);
        PlayersTwo.setEnabled(true);
        PlayersThree.setEnabled(true);
        PlayersFour.setEnabled(true);
        PlayersFive.setEnabled(true);

    }

    private void _onClickListenerSetup(){

        //Color Buttons
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                PlayerColor = Color.RED;
                redButton.setEnabled(false);
            }
        });
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                PlayerColor = Color.YELLOW;
                yellowButton.setEnabled(false);
            }
        });
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                PlayerColor = Color.BLUE;
                blueButton.setEnabled(false);
            }
        });
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                PlayerColor = Color.GREEN;
                greenButton.setEnabled(false);
            }
        });
        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                PlayerColor = Color.BLACK;
                blackButton.setEnabled(false);
            }
        });


        //Number Buttons
        PlayersTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                NumberOfPlayers = 2;
                PlayersTwo.setEnabled(false);
            }
        });
        PlayersThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                NumberOfPlayers = 3;
                PlayersThree.setEnabled(false);
            }
        });
        PlayersFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                NumberOfPlayers = 4;
                PlayersFour.setEnabled(false);
            }
        });
        PlayersFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _enableAllButtons();
                NumberOfPlayers = 5;
                PlayersFive.setEnabled(false);
            }
        });


        //Create and Cancel Buttons
        CreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameName = GameNameField.getText().toString();

                if (!GameName.equals("")){
                    //create the game
                    String message = _createJoinPresenter.createGame(PlayerColor,GameName,NumberOfPlayers);
                    _displayMessage(message);

                }
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void _displayMessage(String message){
        //Just pop up a toast letting the user know what happened
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }

    private void _initializGuiElements(){
        PlayerColor = Color.RED;
        NumberOfPlayers = 2;


        //inflate widgets
        redButton = (ImageButton) findViewById(R.id.redButton);
        blueButton = (ImageButton) findViewById(R.id.blueButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        blackButton = (ImageButton) findViewById(R.id.blackButton);

        PlayersTwo = (Button) findViewById(R.id.player_2);
        PlayersThree = (Button) findViewById(R.id.player_3);
        PlayersFour = (Button) findViewById(R.id.player_4);
        PlayersFive= (Button) findViewById(R.id.player_5);

        CreateGameButton = (Button) findViewById(R.id.create_game_button);
        CancelButton = (Button) findViewById(R.id.cancel_button);

        GameNameField = (EditText) findViewById(R.id.game_name);


        //Disable selected buttons
        redButton.setEnabled(false);
        PlayersTwo.setEnabled(false);
    }
}
