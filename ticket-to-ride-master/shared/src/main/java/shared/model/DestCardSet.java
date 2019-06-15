package shared.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Created by bdemann on 3/7/18.
 */

public class DestCardSet implements Iterable<DestCard>, Serializable {

    private List<DestCard> cards;

    public DestCardSet(List<DestCard> cards){
        this.cards = cards;
    }

    public List<DestCard> toList() {
        return cards;
    }

    public int size(){
        return cards.size();
    }

    @Override
    public Iterator<DestCard> iterator() {
        return new Iterator<DestCard>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < DestCardSet.this.cards.size();
            }

            @Override
            public DestCard next() {
                return DestCardSet.this.cards.get(index++);
            }

            @Override
            public void remove(){

            }
        };
    }
}
