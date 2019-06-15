package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shared.model.interfaces.Card;

/**
 * Created by bdemann on 3/3/18.
 */

public abstract class Deck<T> implements Serializable {

    List<T> _cards;

    public Deck(List<T> cards){
        this._cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(_cards);
    }

    public abstract void discard(List<T> cards);

    public void addCardsToBottom(List<T> cards) {
        _cards.addAll(cards);
    }

    public List<T> draw(int count){
        List<T> cards = new ArrayList<>();
        for(int i = 0; i < count; i++){
            cards.add(this._cards.get(0));
            _cards.remove(0);
        }
        return cards;
    }

    public abstract void discard(T card);

    public int size() {
        return _cards.size();
    }
}
