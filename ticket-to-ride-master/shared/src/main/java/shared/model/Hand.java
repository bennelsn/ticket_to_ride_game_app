package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.model.interfaces.Card;

/**
 * Created by bdemann on 3/4/18.
 */

public class Hand<T> implements Serializable {
    private List<T> _cards;

    public Hand(List<T> cards) {
        this._cards = cards;
    }

    /**
     * Move card at oldIndex to new index
     * @param oldIndex
     * @param newIndex
     */
    public void changeCardPosition(int oldIndex, int newIndex) {
        T card = _cards.get(oldIndex);
        _cards.remove(oldIndex);
        _cards.add(newIndex, card);
    }

    public T useCard(int index) {
        T card = _cards.get(index);
        _cards.remove(index);
        return card;
    }

    public T useCard(T card) {
        _cards.remove(card);
        return card;
    }

    public List<T> useCards(List<Integer> indices) throws Exception {
        List<T> cards = new ArrayList<>();
        Hand<T> originalHand = new Hand<>(new ArrayList<T>(_cards));
        for(Integer i : indices) {
            //This is where the method is broken. Its going to be messing up our indices as we are going though it. But I don't think that anyone ever calls this and there is an exception just in case.
            cards.add(useCard(i));
        }
        throw new Exception("This method isn't implemented yet. this method will defiantly not work. It's going to mess up the indices a lot and it won't accurately get the position of the card to get.");
    }

    public void addCard(T card) {
        _cards.add(card);
    }

    public void addCards(List<T> newDestCards) {
        for(T card : newDestCards) {
            addCard(card);
        }
    }

    public List<T> get_cards(){
        return _cards;
    }

    public int size() {
        return _cards.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(T card : _cards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }
}
