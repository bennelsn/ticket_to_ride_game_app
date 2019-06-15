package view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.a340team.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

import model.ClientRoot;
import presenter.DrawDestinationsPresenter;
import presenter.IDrawDestinationsPresenter;
import proxies.GameServerProxy;
import shared.model.DestCardSet;
import shared.model.DestCard;


/**
 * activity for drawing dest cards
 * Created by BenNelson on 3/6/18.
 */

public class DrawDestinationsActivity extends AppCompatActivity implements IDrawDestinationsView {

    private ImageButton _destCardOne;
    private ImageButton _destCardTwo;
    private ImageButton _destCardThree;
    private Button _confirm;
    private List<Integer> _chosenCards = new ArrayList<>();
    private IDrawDestinationsPresenter _destinationsPresenter;
    static int FIRST_CARD = 1;
    static int SECOND_CARD = 2;
    static int THIRD_CARD = 3;
    static boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_cards);
        boolean isStartOfGame = this.getIntent().getBooleanExtra(ViewUtilities.GAME_START, true);

        _initializePresenter();
        _initializeButtons(isStartOfGame);
        _initializeDestCards(isStartOfGame);
        _createListeners();


    }

    @Override
    public void onBackPressed(){
        //This makes it so the back button should be disabled
        ViewUtilities.displayMessage("Sorry, you can't change your mind like that!", this);
    }

    //This method will show the client what cards to choose from
    private void _initializeDestCards(boolean isStartOfGame) {
        if(isStartOfGame){
            //We need to get the right cards from the presenter.
            List<DestCard> destCards = _destinationsPresenter.getStarterDestinationCards();
            _displayDestinationCards(destCards);

        }
        //It's not the start of the game, so we need to draw
        else{
            List<DestCard> destCards = _destinationsPresenter.drawDestinationCards();
            _displayDestinationCards(destCards);
        }
    }

    private void _displayDestinationCards(List<DestCard> destCards) {
        ArrayList<Integer> imagePaths = ViewUtilities.createDestCardImagePaths(destCards);
        _destCardOne.setImageResource(imagePaths.get(0));
        _destCardTwo.setImageResource(imagePaths.get(1));
        _destCardThree.setImageResource(imagePaths.get(2));
    }

    //Just make the buttons
    private void _initializeButtons(boolean isStartOfGame) {
        _destCardOne = (ImageButton) findViewById(R.id.destCard1);
        _destCardTwo = (ImageButton) findViewById(R.id.destCard2);
        _destCardThree = (ImageButton) findViewById(R.id.destCard3);
        _confirm = (Button) findViewById(R.id.draw_destinations_confirm);

        if(!isStartOfGame){
            TextView instructions = (TextView) findViewById(R.id.drawDestInstructions);
            instructions.setText(R.string.chooseAtLeastOneDestCard);
        }
    }

    private void _initializePresenter(){
        _destinationsPresenter = new DrawDestinationsPresenter(this);

    }

    private void printDestList(List<DestCard> destList){
        for(DestCard card: destList){
            System.out.println("Card: " + card.getDestination().toString());
        }
    }


    //Make the listeners for the image buttons
    private void _createListeners() {
        final int tint = Color.argb(128, 0, 200, 0);
        _destCardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lock the image button
                _destCardOne.setEnabled(false);
                _destCardOne.setColorFilter(tint); // Green Tint
                _chosenCards.add(FIRST_CARD);
            }
        });

        _destCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lock the image button
                _destCardTwo.setEnabled(false);
                _destCardTwo.setColorFilter(tint); // Green Tint
                _chosenCards.add(SECOND_CARD);

            }
        });

        _destCardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lock the image button
                _destCardThree.setEnabled(false);
                _destCardThree.setColorFilter(tint); // Green Tint
                _chosenCards.add(THIRD_CARD);

            }
        });

        _confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send information to the server
                if((_chosenCards.size() > 1 && isStart) || (_chosenCards.size() > 0 && !isStart)){
                    //Send the chosen cards onto the player, and the discard to the server

                    DestCardSet s = ClientRoot.getClientPlayer().getUnresolvedDestCards();
                    System.out.println("DEST CARDS: " + s.toList().toString());
                    List<DestCard> destList = s.toList();
                    System.out.println("DEST CARD SIZE: " + destList.size());
                    List<DestCard> cardsToRemove = new ArrayList<DestCard>();
//                    printDestList(destList);
                    DestCard second = destList.get(1);
                    DestCard third = destList.get(2);

                    if(!_chosenCards.contains(FIRST_CARD)){
                        cardsToRemove.add(destList.get(0));
                        destList.remove(0);
                    }
                    if(!_chosenCards.contains(SECOND_CARD)){
                        int index = findDestCard(destList,second);
                        cardsToRemove.add(destList.get(index));
                        destList.remove(index);
                    }
                    //System.out.println(destList.get(2));
                    if(!_chosenCards.contains(THIRD_CARD)){
                        int index = findDestCard(destList,third);
                        cardsToRemove.add(destList.get(index));
                        destList.remove(index);
                    }

//                    printDestList(destList);
                    DestCardSet removeDestCardSet = new DestCardSet(cardsToRemove);
                    _destinationsPresenter.discardDestCards(destList, removeDestCardSet);


                    //Then finish the activity
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.finish();
                }
                else{
                    ViewUtilities.displayMessage("You need more cards.", view.getContext());
                }

                isStart = false;

            }
        });
    }

    private int findDestCard(List<DestCard> destCards, DestCard card){
        int i;
        for(i = 0; i < destCards.size(); i++){
            DestCard currentCard = destCards.get(i);
            if(currentCard.getDestination().equals(card.getDestination()) && currentCard.getStartingPoint().equals(card.getStartingPoint())){
                return i;
            }
        }

        return -1;
    }

}
