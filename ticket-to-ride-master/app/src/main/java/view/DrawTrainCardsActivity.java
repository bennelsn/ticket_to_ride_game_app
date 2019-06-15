package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.a340team.tickettoride.R;

import java.util.List;

import model.ClientRoot;
import presenter.DrawTrainCardsPresenter;
import shared.model.Color;
import shared.model.TrainCard;
import shared.model.interfaces.IGameInfo;

/**
 *
 * Created by BenNelson on 3/6/18.
 */

public class DrawTrainCardsActivity extends AppCompatActivity implements IDrawTrainCardsView{

    private ImageButton[] _trainCards;
    private ImageButton _trainCardDeck;
    private DrawTrainCardsPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _trainCards = new ImageButton[5];
        setContentView(R.layout.activity_draw_trains);
        _initializePresenter();
        _initializeImageButtons(ClientRoot.getClientGameInfo());
        _createOnClickListeners();

    }

    private void _initializePresenter(){
        _presenter = new DrawTrainCardsPresenter(this);
    }

    private void _initializeImageButtons(IGameInfo gameInfo){

        _trainCards[0] = (ImageButton) findViewById(R.id.train_card_one);
        _trainCards[1] = (ImageButton) findViewById(R.id.train_card_two);
        _trainCards[2] = (ImageButton) findViewById(R.id.train_card_three);
        _trainCards[3] = (ImageButton) findViewById(R.id.train_card_four);
        _trainCards[4] = (ImageButton) findViewById(R.id.train_card_five);
        _trainCardDeck = (ImageButton) findViewById(R.id.train_card_deck);

        for(int i = 0; i < 5; i++) {
            _trainCards[i].setImageResource(_getCardImage(gameInfo.getFaceUpCards().get(i)));
        }

        setFaceUpCards(gameInfo.getFaceUpCards());
    }

    public void setFaceUpCard(int index, TrainCard trainCard) {
        _trainCards[index].setImageResource(_getCardImage(trainCard));
    }

    public void setFaceUpCards(List<TrainCard> faceUpCards) {
        for(int i = 0; i < 5; i++) {
            setFaceUpCard(i, faceUpCards.get(i));
            _trainCards[i].invalidate();
        }
    }

    private void _createOnClickListeners() {

        _trainCards[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceUpCard(0);
            }
        });

        _trainCards[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceUpCard(1);
            }
        });

        _trainCards[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceUpCard(2);
            }
        });

        _trainCards[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceUpCard(3);
            }
        });

        _trainCards[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceUpCard(4);
            }
        });

        _trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawFaceDownCard();
            }
        });
    }

    private int _getCardImage(TrainCard trainCard) {
        if (trainCard.getColor().equals(Color.BLACK)) {
            return R.drawable.black;
        } else if (trainCard.getColor().equals(Color.BLUE)) {
            return R.drawable.blue;
        } else if (trainCard.getColor().equals(Color.GREEN)) {
            return R.drawable.green;
        } else if (trainCard.getColor().equals(Color.ORANGE)) {
            return R.drawable.orange;
        } else if (trainCard.getColor().equals(Color.YELLOW)) {
            return R.drawable.yellow;
        } else if (trainCard.getColor().equals(Color.WHITE)) {
            return R.drawable.white;
        } else if (trainCard.getColor().equals(Color.RED)) {
            return R.drawable.red;
        } else if (trainCard.getColor().equals(Color.PINK)) {
            return R.drawable.pink;
        } else {//RAINBOW
            return R.drawable.rainbow;
        }
    }

    @Override
    public void drawFaceUpCard(int cardNum) {
        ViewUtilities.displayMessage(_presenter.drawFaceUpCard(cardNum), this);
    }

    @Override
    public void drawFaceDownCard() {
        ViewUtilities.displayMessage(_presenter.drawFaceDownCard(), this);
    }
}
