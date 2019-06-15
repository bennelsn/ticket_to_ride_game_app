package view;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a340team.tickettoride.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import facade.guifacade.GameGuiFacade;
import facade.guifacade.GameOverGuiFacade;
import model.ClientRoot;
import presenter.GamePresenter;
import presenter.IGamePresenter;
import shared.model.Route;
import shared.model.TrainCard;
import shared.model.TrainCardSet;
import shared.model.initialized_info.Routes;
import shared.model.Color.*;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IRoute;

public class GameActivity extends AppCompatActivity implements IGameActivity{

    //Map
    ImageView mapView;
    //Click Buttons
    Button _drawTrains;
    Button _claimRoute;
    Button _drawDestinations;
    Button _myGame;
    Button _chatStats;
    Button _endGame;

    //Card Selection ImageViews and TextViews
    ImageView _redCardImage;
    ImageView _whiteCardImage;
    ImageView _orangeCardImage;
    ImageView _greenCardImage;
    ImageView _blackCardImage;
    ImageView _blueCardImage;
    ImageView _yellowCardImage;
    ImageView _rainbowCardImage;
    ImageView _pinkCardImage;
    TextView _redCardNumber;
    TextView _whiteCardNumber;
    TextView _orangeCardNumber;
    TextView _greenCardNumber;
    TextView _blackCardNumber;
    TextView _blueCardNumber;
    TextView _yellowCardNumber;
    TextView _rainbowCardNumber;
    TextView _pinkCardNumber;
    TextView _pickTrainInstructions;
    Button _confirmClaimButton;
    Button _selectCardsButton;
    private int _redCard = 0;
    private int _whiteCard = 0;
    private int _orangeCard = 0;
    private int _greenCard = 0;
    private int _blackCard = 0;
    private int _blueCard = 0;
    private int _yellowCard = 0;
    private int _rainbowCard = 0;
    private int _pinkCard = 0;

    //Cities
    Button portland;
    Button vancouver;
    Button seattle;
    Button san_francisco;
    Button los_angeles;
    Button calgary;
    Button helena;
    Button salt_lake_city;
    Button las_vegas;
    Button phoenix;
    Button el_paso;
    Button santa_fe;
    Button denver;
    Button winnipeg;
    Button duluth;
    Button omaha;
    Button kansas_city;
    Button oklahoma_city;
    Button dallas;
    Button houston;
    Button saul_st_marie;
    Button chicago;
    Button st_louis;
    Button little_rock;
    Button new_orleans;
    Button nashville;
    Button atlanta;
    Button toronto;
    Button pittsburgh;
    Button charleston;
    Button raleigh;
    Button washington;
    Button new_york;
    Button boston;
    Button montreal;
    Button miami;
    //Button Array
    ArrayList<Button> _cityButtons;
    ArrayList<String> _citiesSelected = new ArrayList<>();
    private final int _maxCitiesSelected = 2;
    private IGamePresenter _gamePresenter;
    private final int INVISIBLE = View.GONE;
    private final int VISIBLE = View.VISIBLE;
    private boolean _cardPickerVisible;
    private final int tint = Color.argb(50, 0, 0, 0);
    private final int notint = Color.argb(0, 0, 0, 0);
    private List<TrainCard> userInputCards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_acitvity);
        _gamePresenter = new GamePresenter(this);
        //Make the mape
        mapView = (ImageView) findViewById(R.id.map_image);


        //Initialize Components
        _initializeCardPicker();
        _createCardPickerListeners();
        _initializeButtons();
        _createOnClickListeners(_cityButtons);
        _createFunctionButtonListeners();
        _setCityButtons(false);
        checkTurn();
        IGameInfo gi = ClientRoot.getClientGameInfo();
        System.out.println("#@$(*@#$@#%$8632948@#_($)*#@(*$&#@^&$(*&$#@(*%(*#@FHELDSF ");
        System.out.println("active player" + gi.activePlayer());
        System.out.println("turn_index" + gi.getTurnIndex());
//        System.out.println(gi.getGameHistory().getEvents().get(0));
        System.out.println("gamename" + gi.getGameName());
        if (this.getIntent().getBooleanExtra(ViewUtilities.GAME_START, true)) {
            displayStartDestCards();
        }

    }

    private void _createCardPickerListeners() {
         _redCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _redCard++;
                 if(_checkIfUserHasCard(shared.model.Color.RED, _redCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.RED,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _redCard--;
                 }
             }
         });
         _whiteCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _whiteCard++;
                 if(_checkIfUserHasCard(shared.model.Color.WHITE, _whiteCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.WHITE,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _whiteCard--;
                 }

             }
         });
         _orangeCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _orangeCard++;
                 if(_checkIfUserHasCard(shared.model.Color.ORANGE, _orangeCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.ORANGE,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _orangeCard--;
                 }

             }
         });
         _greenCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _greenCard++;
                 if(_checkIfUserHasCard(shared.model.Color.GREEN, _greenCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.GREEN,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _greenCard--;
                 }

             }
         });
         _blackCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _blackCard++;
                 if(_checkIfUserHasCard(shared.model.Color.BLACK, _blackCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.BLACK,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _blackCard--;
                 }

             }
         });
         _blueCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _blueCard++;
                 if(_checkIfUserHasCard(shared.model.Color.BLUE, _blueCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.BLUE,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _blueCard--;
                 }

             }
         });
         _yellowCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _yellowCard++;
                 if(_checkIfUserHasCard(shared.model.Color.YELLOW, _yellowCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.YELLOW,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _yellowCard--;
                 }

             }
         });
         _rainbowCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _rainbowCard++;
                 if(_checkIfUserHasCard(shared.model.Color.RAINBOW, _rainbowCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.RAINBOW,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _rainbowCard--;
                 }

             }
         });
         _pinkCardImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _pinkCard++;
                 if(_checkIfUserHasCard(shared.model.Color.PINK, _pinkCard)) {
                     userInputCards.add(new TrainCard(shared.model.Color.PINK,0));
                     _updateCardPickerNumbers();
                 }
                 else{
                     _pinkCard--;
                 }

             }
         });

         _confirmClaimButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 _confirmClaimButtonClick();
             }
         });
         _selectCardsButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(_cardPickerVisible){
                     _setCardPickerVisibility(INVISIBLE);
                 }
                 else{
                     _setCardPickerVisibility(VISIBLE);
                 }
             }
         });
        _endGame = (Button) findViewById(R.id.end_game);
        _endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameOverGuiFacade gameOverGuiFacade = new GameOverGuiFacade();
                gameOverGuiFacade.getPointTotals();
                Intent intent = new Intent(v.getContext(), GameOverActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean _checkIfUserHasCard(shared.model.Color color,int cardCount) {
        return _gamePresenter.userHasCards(color, cardCount);
    }

    private void _updateCardPickerNumbers(){
        String red = Integer.toString(_redCard);
        String white = Integer.toString(_whiteCard);
        String orange = Integer.toString(_orangeCard);
        String green = Integer.toString(_greenCard);
        String black = Integer.toString(_blackCard);
        String blue = Integer.toString(_blueCard);
        String yellow = Integer.toString(_yellowCard);
        String rainbow = Integer.toString(_rainbowCard);
        String pink = Integer.toString(_pinkCard);
        _redCardNumber.setText(red);
        _whiteCardNumber.setText(white);
        _orangeCardNumber.setText(orange);
        _greenCardNumber.setText(green);
        _blackCardNumber.setText(black);
        _blueCardNumber.setText(blue);
        _yellowCardNumber.setText(yellow);
        _rainbowCardNumber.setText(rainbow);
        _pinkCardNumber.setText(pink);
    }

    private void _confirmClaimButtonClick() {
        if(_citiesSelected.size() == 2) {

            //Check if the two cities comprise a valid edge
            String message = _gamePresenter.claimRoute(_citiesSelected, new TrainCardSet(userInputCards));
            userInputCards.clear();

            //Re-enable buttons, turn off tint, and let the use know what just happened.
            ViewUtilities.displayMessage(message, this);
            _setCityButtons(false);
            //_setDrawButtons(true);
            //_claimRoute.setEnabled(true);
            _citiesSelected.clear();
            _setCardPickerVisibility(INVISIBLE);
            _setCardPickerButtonsVisibility(INVISIBLE);
            _zeroOutCardPicker();
            mapView.setColorFilter(notint);
        }
        else{
            ViewUtilities.displayMessage("Must Claim a Route", this);
        }
    }

    private void _initializeCardPicker() {
        _redCardImage = (ImageView) findViewById(R.id.player_red_train_card);
         _whiteCardImage = (ImageView) findViewById(R.id.player_white_train_card);
         _orangeCardImage = (ImageView) findViewById(R.id.player_orange_train_card);
         _greenCardImage = (ImageView) findViewById(R.id.player_green_train_card);
         _blackCardImage = (ImageView) findViewById(R.id.player_black_train_card);
         _blueCardImage = (ImageView) findViewById(R.id.player_blue_train_card);
         _yellowCardImage = (ImageView) findViewById(R.id.player_yellow_train_card);
         _rainbowCardImage = (ImageView) findViewById(R.id.player_rainbow_train_card);
         _pinkCardImage = (ImageView) findViewById(R.id.player_pink_train_card);
         _redCardNumber = (TextView) findViewById(R.id.red_number);
         _whiteCardNumber = (TextView) findViewById(R.id.white_number);
        _orangeCardNumber = (TextView) findViewById(R.id.orange_number);
        _greenCardNumber = (TextView) findViewById(R.id.green_number);
        _blackCardNumber = (TextView) findViewById(R.id.black_number);
        _blueCardNumber = (TextView) findViewById(R.id.blue_number);
        _yellowCardNumber = (TextView) findViewById(R.id.yellow_number);
        _rainbowCardNumber = (TextView) findViewById(R.id.rainbow_number);
        _pinkCardNumber = (TextView) findViewById(R.id.pink_number);
        _pickTrainInstructions = (TextView) findViewById(R.id.PickTrainColorTextView);
      _zeroOutCardPicker();

        _confirmClaimButton = (Button) findViewById(R.id.confirm_claim);
        _selectCardsButton = (Button) findViewById(R.id.display_card_picker);
        _setCardPickerVisibility(INVISIBLE);
        _setCardPickerButtonsVisibility(INVISIBLE);
    }

    private void _zeroOutCardPicker() {
        String zero = "0";
        _redCard = 0;
        _whiteCard = 0;
        _orangeCard = 0;
        _greenCard = 0;
        _blackCard = 0;
        _blueCard = 0;
        _yellowCard = 0;
        _rainbowCard = 0;
        _pinkCard = 0;

        _redCardNumber.setText(zero);
        _whiteCardNumber.setText(zero);
        _orangeCardNumber.setText(zero);
        _greenCardNumber.setText(zero);
        _blackCardNumber.setText(zero);
        _blueCardNumber.setText(zero);
        _yellowCardNumber.setText(zero);
        _rainbowCardNumber.setText(zero);
        _pinkCardNumber.setText(zero);
    }

    private void _setCardPickerButtonsVisibility(int vis) {
        _confirmClaimButton.setVisibility(vis);
        _selectCardsButton.setVisibility(vis);
    }

    private void _setCardPickerVisibility(int visibility) {
         _redCardImage.setVisibility(visibility);
         _whiteCardImage.setVisibility(visibility);
         _orangeCardImage.setVisibility(visibility);
         _greenCardImage.setVisibility(visibility);
         _blackCardImage.setVisibility(visibility);
         _blueCardImage.setVisibility(visibility);
         _yellowCardImage.setVisibility(visibility);
         _rainbowCardImage.setVisibility(visibility);
         _pinkCardImage.setVisibility(visibility);
         _redCardNumber.setVisibility(visibility);
         _whiteCardNumber.setVisibility(visibility);
         _orangeCardNumber.setVisibility(visibility);
         _greenCardNumber.setVisibility(visibility);
         _blackCardNumber.setVisibility(visibility);
         _blueCardNumber.setVisibility(visibility);
         _yellowCardNumber.setVisibility(visibility);
         _rainbowCardNumber.setVisibility(visibility);
         _pinkCardNumber.setVisibility(visibility);
        _pickTrainInstructions.setVisibility(visibility);

        if(visibility == VISIBLE){
            _cardPickerVisible = true;
        }
        else{
            _cardPickerVisible = false;
        }
    }

    private void _createOnClickListeners(List<Button> _cityButtons){

         final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.atlanta);
         View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _cityListeners(view, mediaPlayer);
            }
        };

        for (int i = 0; i < _cityButtons.size(); i++) {
            _cityButtons.get(i).setOnClickListener(clickListener);
        }
    }

    private void _cityListeners(View view, MediaPlayer mediaPlayer) {
        if(!_cardPickerVisible) {
            if (_citiesSelected.size() < _maxCitiesSelected) {
                Button button = (Button) view;
                String text = button.getText().toString();

                //Add the city to the selected list
                _citiesSelected.add(text);
                ViewUtilities.displayMessage(text, view.getContext());

            } else {
                ViewUtilities.displayMessage("You've Selected 2 Cities\nPress Claim Routes to End", view.getContext());
            }
        }
    }

    private void _createFunctionButtonListeners(){
        _drawTrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawTrainCards();
            }
        });

        _claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                claimRoute();
            }
        });

        _drawDestinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawDestinations();
            }
        });

        _myGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGame();
            }
        });

        _chatStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChat();
            }
        });
    }

    public void checkTurn(){
        boolean isTurn = _gamePresenter.checkTurn();

        if(!isTurn){
            _drawTrains.setEnabled(false);
            _claimRoute.setEnabled(false);
            _drawDestinations.setEnabled(false);
        }
        else {
            _drawTrains.setEnabled(true);
            _claimRoute.setEnabled(true);
            _drawDestinations.setEnabled(true);
        }
    }

    private void _initializeButtons(){
        //Click buttons
        _drawTrains = (Button) findViewById(R.id.draw_trains);
        _claimRoute = (Button) findViewById(R.id.claim_route);
        _drawDestinations = (Button) findViewById(R.id.draw_destinations);
        _myGame = (Button) findViewById(R.id.my_game);
        _chatStats = (Button) findViewById(R.id.chats_stats);


        //Enables/Desables buttons if player's turn
        checkTurn();

        //find buttons
        portland = (Button) findViewById(R.id.portland);
        new_york = (Button) findViewById(R.id.new_york);
        vancouver = (Button) findViewById(R.id.vancouver);
        seattle = (Button) findViewById(R.id.seattle);
        san_francisco = (Button) findViewById(R.id.san_francisco);
        los_angeles = (Button) findViewById(R.id.los_angeles);
        calgary = (Button) findViewById(R.id.calgary);
        helena = (Button) findViewById(R.id.helena);
        denver = (Button) findViewById(R.id.denver);
        santa_fe = (Button) findViewById(R.id.santa_fe);
        el_paso = (Button) findViewById(R.id.el_paso);
        salt_lake_city = (Button) findViewById(R.id.salt_lake_city);
        winnipeg = (Button) findViewById(R.id.winnipeg);
        duluth = (Button) findViewById(R.id.duluth);
        omaha = (Button) findViewById(R.id.omaha);
        kansas_city = (Button) findViewById(R.id.kansas_city);
        oklahoma_city = (Button) findViewById(R.id.oklahoma_city);
        dallas = (Button) findViewById(R.id.dallas);
        houston = (Button) findViewById(R.id.houston);
        new_orleans = (Button) findViewById(R.id.new_orleans);
        little_rock = (Button) findViewById(R.id.little_rock);
        st_louis = (Button) findViewById(R.id.st_louis);
        chicago = (Button) findViewById(R.id.chicago);
        saul_st_marie = (Button) findViewById(R.id.saul_st_marie);
        nashville = (Button) findViewById(R.id.nashville);
        atlanta = (Button) findViewById(R.id.atlanta);
        charleston = (Button) findViewById(R.id.charleston);
        raleigh = (Button) findViewById(R.id.raleigh);
        washington = (Button) findViewById(R.id.washington);
        pittsburgh = (Button) findViewById(R.id.pittsburgh);
        new_york = (Button) findViewById(R.id.new_york);
        boston = (Button) findViewById(R.id.boston);
        montreal = (Button) findViewById(R.id.montreal);
        las_vegas = (Button) findViewById(R.id.las_vegas);
        phoenix = (Button) findViewById(R.id.phoenix);
        toronto = (Button) findViewById(R.id.toronto);
        miami = (Button) findViewById(R.id.miami);


        //Add buttons to list
        _cityButtons = new ArrayList<>();
        _cityButtons.add(portland);
        _cityButtons.add(vancouver);
        _cityButtons.add(seattle);
        _cityButtons.add(san_francisco);
        _cityButtons.add(los_angeles);
        _cityButtons.add(calgary);
        _cityButtons.add(helena);
        _cityButtons.add(salt_lake_city);
        _cityButtons.add(las_vegas);
        _cityButtons.add(phoenix);
        _cityButtons.add(el_paso);
        _cityButtons.add(santa_fe);
        _cityButtons.add(denver);
        _cityButtons.add(winnipeg);
        _cityButtons.add(duluth);
        _cityButtons.add(omaha);
        _cityButtons.add(kansas_city);
        _cityButtons.add(oklahoma_city);
        _cityButtons.add(dallas);
        _cityButtons.add(houston);
        _cityButtons.add(saul_st_marie);
        _cityButtons.add(chicago);
        _cityButtons.add(st_louis);
        _cityButtons.add(little_rock);
        _cityButtons.add(new_orleans);
        _cityButtons.add(nashville);
        _cityButtons.add(atlanta);
        _cityButtons.add(toronto);
        _cityButtons.add(pittsburgh);
        _cityButtons.add(charleston);
        _cityButtons.add(raleigh);
        _cityButtons.add(washington);
        _cityButtons.add(new_york);
        _cityButtons.add(boston);
        _cityButtons.add(montreal);
        _cityButtons.add(miami);
    }

    private void _setCityButtons(boolean value){
        portland.setEnabled(value);
        vancouver.setEnabled(value);
        seattle.setEnabled(value);
        san_francisco.setEnabled(value);
        los_angeles.setEnabled(value);
        calgary.setEnabled(value);
        helena.setEnabled(value);
        salt_lake_city.setEnabled(value);
        las_vegas.setEnabled(value);
        phoenix.setEnabled(value);
        el_paso.setEnabled(value);
        santa_fe.setEnabled(value);
        denver.setEnabled(value);
        winnipeg.setEnabled(value);
        duluth.setEnabled(value);
        omaha.setEnabled(value);
        kansas_city.setEnabled(value);
        oklahoma_city.setEnabled(value);
        dallas.setEnabled(value);
        houston.setEnabled(value);
        saul_st_marie.setEnabled(value);
        chicago.setEnabled(value);
        st_louis.setEnabled(value);
        little_rock.setEnabled(value);
        new_orleans.setEnabled(value);
        nashville.setEnabled(value);
        atlanta.setEnabled(value);
        toronto.setEnabled(value);
        pittsburgh.setEnabled(value);
        charleston.setEnabled(value);
        raleigh.setEnabled(value);
        washington.setEnabled(value);
        new_york.setEnabled(value);
        boston.setEnabled(value);
        montreal.setEnabled(value);
        miami.setEnabled(value);
    }

    private void _setDrawButtons(boolean value) {
        _drawTrains.setEnabled(value);
        _drawDestinations.setEnabled(value);
    }

    @Override
    public void onBackPressed() {
        //We don't want them to leave the game.
        ViewUtilities.displayMessage("You Can't Leave The Game.\n " +
                "Never Give Up.", this);
    }

    @Override
    public void displayStartDestCards() {
        Intent intent = new Intent(this, DrawDestinationsActivity.class);
        intent.putExtra(ViewUtilities.GAME_START, true);
        startActivity(intent);
    }

    @Override
    public void drawTrainCards() {
        Intent intent = new Intent(this, DrawTrainCardsActivity.class);
        startActivity(intent);
    }

    @Override
    public void claimRoute() {
        _setCityButtons(true);
        _setDrawButtons(false);
        _claimRoute.setEnabled(false);
        _setCardPickerButtonsVisibility(VISIBLE);
        mapView.setColorFilter(tint);
    }

    public void drawRoutes() {
        //Get all the routes to draw.
        Map<shared.model.Color, List<IRoute>> routes = _gamePresenter.getRoutesMapForDrawing();

        //Draw the routes
        DrawUtilities myDrawer = new DrawUtilities(this);
        myDrawer.drawRoutes(routes, mapView);
    }

    @Override
    public void drawDestinations() {
        Intent intent = new Intent(this, DrawDestinationsActivity.class);
        intent.putExtra(ViewUtilities.GAME_START, false);
        startActivity(intent);

    }

    @Override
    public void displayGame() {
        Intent intent = new Intent(this, GameInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

}