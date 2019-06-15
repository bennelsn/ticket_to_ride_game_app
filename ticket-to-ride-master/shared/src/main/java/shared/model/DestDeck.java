package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shared.model.initialized_info.DestinationCards;

/**
 * Created by bdemann on 3/3/18.
 */

public class DestDeck extends Deck<DestCard> implements Serializable {

    //My justification for having a sepearte class for the destination deck is so that this class can worry about properly reading the cards from the DestinationCards class.
    public DestDeck(){
        super(DestinationCards.getDestinationCards());
    }

    @Override
    public void discard(List cards) {
        super.addCardsToBottom(cards);
    }

    @Override
    public void discard(DestCard card) {
        List<DestCard> discardedCard = new ArrayList<>();
        discardedCard.add(card);
        super.addCardsToBottom(discardedCard);
    }
}
