package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shared.model.initialized_info.TrainCards;
import shared.model.interfaces.Card;

/**
 * Created by bdemann on 3/3/18.
 */

public class TrainDeck extends Deck<TrainCard> implements Serializable {

    private List<TrainCard> discardPile;

    public TrainDeck(){
        super(new ArrayList<>(TrainCards.getTrainCards()));
        discardPile = new ArrayList<>();
    }

    @Override
    public void discard(List<TrainCard> cards) {
        discardPile.addAll(cards);
    }

    @Override
    public void discard(TrainCard card) {
        discardPile.add(card);
    }

    public void shuffleDiscardPile() {
        Collections.shuffle(discardPile);
        super.addCardsToBottom(discardPile);
        discardPile = new ArrayList<>();
    }
}
