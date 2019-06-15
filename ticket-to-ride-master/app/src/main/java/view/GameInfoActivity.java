package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a340team.tickettoride.R;

import java.util.List;
import java.util.Map;

import presenter.GameInfoPresenter;
import presenter.IGameInfoPresenter;
import shared.model.TrainCard;
import shared.model.interfaces.IRoute;
import shared.model.interfaces.IGameInfo;

public class GameInfoActivity extends AppCompatActivity {

    private TextView _players[];
    private TextView _playerPoints[];
    private TextView _playerTrains[];
    private TextView _playerCards[];
    private TextView _playerRoutes[];
    private TextView _playerColor[];
    private TextView _playerDestCards[];

    private TextView _redNumber;
    private TextView _greenNumber;
    private TextView _blueNumber;
    private TextView _orangeNumber;
    private TextView _yellowNumber;
    private TextView _pinkNumber;
    private TextView _blackNumber;
    private TextView _whiteNumber;
    private TextView _rainbowNumber;

    private IGameInfoPresenter _gameInfoPresenter;

    private DestinationCardRecyclerAdapter _adapter;
    private RecyclerView _destinationRecycler;

    private Button _doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int numPlayers = 5;

        _players = new TextView[numPlayers];
        _playerPoints = new TextView[numPlayers];
        _playerTrains = new TextView[numPlayers];
        _playerCards = new TextView[numPlayers];
        _playerRoutes = new TextView[numPlayers];
        _playerColor = new TextView[numPlayers];
        _playerDestCards = new TextView[numPlayers];

        setContentView(R.layout.activity_game_info);

        _doneButton = (Button) findViewById(R.id.game_info_done);

        _doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        _initializeGuiElements();
        _initializePresenter();
        _initializeInfo();
        _initializePlayerHand();
        _setUpRecycler();
    }

    private void _setUpRecycler(){
        _destinationRecycler = (RecyclerView) findViewById(R.id.destination_recycler);
        _adapter = new DestinationCardRecyclerAdapter(_gameInfoPresenter.getDestinationCards(),
                _gameInfoPresenter.getCompleteDestinations());
        _destinationRecycler.setAdapter(_adapter);
        _destinationRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void _initializePresenter(){
        _gameInfoPresenter = new GameInfoPresenter(this);
    }

    private void _initializeGuiElements(){
        _players[0] = (TextView) findViewById(R.id.player1);
        _players[1] = (TextView) findViewById(R.id.player2);
        _players[2] = (TextView) findViewById(R.id.player3);
        _players[3] = (TextView) findViewById(R.id.player4);
        _players[4] = (TextView) findViewById(R.id.player5);

        _playerPoints[0] = (TextView) findViewById(R.id.player1_points);
        _playerPoints[1] = (TextView) findViewById(R.id.player2_points);
        _playerPoints[2] = (TextView) findViewById(R.id.player3_points);
        _playerPoints[3] = (TextView) findViewById(R.id.player4_points);
        _playerPoints[4] = (TextView) findViewById(R.id.player5_points);

        _playerTrains[0] = (TextView) findViewById(R.id.player1_trains);
        _playerTrains[1] = (TextView) findViewById(R.id.player2_trains);
        _playerTrains[2] = (TextView) findViewById(R.id.player3_trains);
        _playerTrains[3] = (TextView) findViewById(R.id.player4_trains);
        _playerTrains[4] = (TextView) findViewById(R.id.player5_trains);

        _playerCards[0] = (TextView) findViewById(R.id.player1_cards);
        _playerCards[1] = (TextView) findViewById(R.id.player2_cards);
        _playerCards[2] = (TextView) findViewById(R.id.player3_cards);
        _playerCards[3] = (TextView) findViewById(R.id.player4_cards);
        _playerCards[4] = (TextView) findViewById(R.id.player5_cards);

        _playerRoutes[0] = (TextView) findViewById(R.id.player1_routes);
        _playerRoutes[1] = (TextView) findViewById(R.id.player2_routes);
        _playerRoutes[2] = (TextView) findViewById(R.id.player3_routes);
        _playerRoutes[3] = (TextView) findViewById(R.id.player4_routes);
        _playerRoutes[4] = (TextView) findViewById(R.id.player5_routes);

        _playerColor[0] = (TextView) findViewById(R.id.player1_color);
        _playerColor[1] = (TextView) findViewById(R.id.player2_color);
        _playerColor[2] = (TextView) findViewById(R.id.player3_color);
        _playerColor[3] = (TextView) findViewById(R.id.player4_color);
        _playerColor[4] = (TextView) findViewById(R.id.player5_color);

        _playerDestCards[0] = (TextView) findViewById(R.id.player1_dest_cards);
        _playerDestCards[1] = (TextView) findViewById(R.id.player2_dest_cards);
        _playerDestCards[2] = (TextView) findViewById(R.id.player3_dest_cards);
        _playerDestCards[3] = (TextView) findViewById(R.id.player4_dest_cards);
        _playerDestCards[4] = (TextView) findViewById(R.id.player5_dest_cards);

        _redNumber = (TextView) findViewById(R.id.red_number);
        _greenNumber = (TextView) findViewById(R.id.green_number);
        _blueNumber = (TextView) findViewById(R.id.blue_number);
        _pinkNumber = (TextView) findViewById(R.id.pink_number);
        _orangeNumber = (TextView) findViewById(R.id.orange_number);
        _yellowNumber = (TextView) findViewById(R.id.yellow_number);
        _blackNumber = (TextView) findViewById(R.id.black_number);
        _whiteNumber = (TextView) findViewById(R.id.white_number);
        _rainbowNumber = (TextView) findViewById(R.id.rainbow_number);
    }

    private void _initializeInfo() {
        IGameInfo gameInfo = _gameInfoPresenter.getStarterGameInfo();
        System.out.println("Size player list in Activity: " + gameInfo.getPlayers().size());
        System.out.println("TURN INDEX: " + gameInfo.getTurnIndex());
        _updateGameInfo(gameInfo.getPlayers(), gameInfo.getPlayerPoints(), gameInfo.getPlayerHandSizes(),
                gameInfo.getClaimedRoutes(), gameInfo.getRemainingTrains(), gameInfo.getPlayerColors(), gameInfo.getPlayerDestCount());

        /* WAS FROM DEMO - DIDNT delete this just in case it's useful later.
        if(!isDemoDone) {
            _player1.setBackgroundColor(Color.argb(0,255,255,255));
            _player2.setBackgroundColor(Color.argb(255,220,220,220));
        }
        else{
            _player1.setBackgroundColor(Color.argb(255,220,220,220));
            _player2.setBackgroundColor(Color.argb(0,255,255,255));
        }
        */

    }

    private void _initializePlayerHand(){
        List<TrainCard> playerHand = _gameInfoPresenter.getStarterPlayerHand();
        _updatePlayerHand(playerHand);

    }

    private String numberOfRoutes(Map<String, IRoute> claimedRoutes, String player){
        System.out.println("Size of claimedRoutes: " + claimedRoutes.size());

        int number = 0;
        for (Map.Entry<String, IRoute> entry : claimedRoutes.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            if(entry.getKey().equals(player)){
                number++;
            }
        }

        String numAsStr = Integer.toString(number);
        return numAsStr;
    }

    private String _getNumberOfCards(List<TrainCard> playerHand, String color){
        int cardCount = 0;
        for(TrainCard trainCard : playerHand){
            if(trainCard.getColor().toString().equals(color)){
                cardCount++;
            }
        }
        String numAsStr = Integer.toString(cardCount);
        return numAsStr;
    }

    public void _updatePlayerHand(List<TrainCard> playerHand){
        if(playerHand.size() == 0){
            String zero = "0";
            _redNumber.setText(zero);
            _greenNumber.setText(zero);
            _blueNumber.setText(zero);
            _pinkNumber.setText(zero);
            _orangeNumber.setText(zero);
            _yellowNumber.setText(zero);
            _blackNumber.setText(zero);
            _whiteNumber.setText(zero);
            _rainbowNumber.setText(zero);
        }
        else{
            _redNumber.setText(_getNumberOfCards(playerHand, "red"));
            _greenNumber.setText(_getNumberOfCards(playerHand, "green"));
            _blueNumber.setText( _getNumberOfCards(playerHand, "blue"));
            _pinkNumber.setText(_getNumberOfCards(playerHand, "pink"));
            _orangeNumber.setText(_getNumberOfCards(playerHand, "orange"));
            _yellowNumber.setText(_getNumberOfCards(playerHand, "yellow"));
            _blackNumber.setText(_getNumberOfCards(playerHand, "black"));
            _whiteNumber.setText(_getNumberOfCards(playerHand, "white"));
            _rainbowNumber.setText(_getNumberOfCards(playerHand, "rainbow"));
        }
    }


    public void _updateGameInfo(List<String> players, Map<String, Integer> playerPoints, Map<String, Integer> playerHandSize,
                                Map<String, List<IRoute>> claimedRoutes, Map<String, Integer> playerRemainingTrains,
                                Map<String, Integer> playerColors, Map<String, Integer> destCards){

        int numPlayers = players.size();
        int maxPlayers = 5;

        //Initialize all players to blank.
        for(int i = 0; i < maxPlayers; i++) {
            _players[i].setText("--");
            _playerPoints[i].setText("--");
            _playerCards[i].setText("--");
            _playerTrains[i].setText("--");
            _playerRoutes[i].setText("--");
            _playerColor[i].setText("--");
            _playerDestCards[i].setText("--");
        }

        //Update all the players that are in the game
        for(int i = 0; i < numPlayers; i++) {
            _players[i].setText(players.get(i));
            _playerPoints[i].setText(playerPoints.get(players.get(i)).toString());
            _playerCards[i].setText(playerHandSize.get(players.get(i)).toString());
            _playerTrains[i].setText(playerRemainingTrains.get(players.get(i)).toString());
            _playerRoutes[i].setText(Integer.toString(claimedRoutes.get(players.get(i)).size()));
            _playerColor[i].setBackgroundColor(playerColors.get(players.get(i)));
            _playerDestCards[i].setText(destCards.get(players.get(i)).toString());
        }
    }
}
