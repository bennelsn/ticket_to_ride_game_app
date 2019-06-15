package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by bdemann on 3/5/18.
 */

public class TrainCardSet implements Serializable, Iterable<TrainCard> {

    public List<TrainCard> trainCards;

    public TrainCardSet(List<TrainCard> trainCards){
        this.trainCards = trainCards;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public Color getSetColor() {
        for( TrainCard card : this) {
            if (card.getColor().equals(Color.RAINBOW)) {
                continue;
            }
            return card.getColor();
        }
        return Color.RAINBOW;
    }

    public boolean colorsMatch() {
        Color currColor = null;
        for( TrainCard card : this) {
            if (card.getColor().equals(Color.RAINBOW)) {
                continue;
            }
            if (currColor == null){
                currColor = card.getColor();
            }else if (!currColor.equals(card.getColor())){
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<TrainCard> iterator() {
        return new Iterator<TrainCard>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < TrainCardSet.this.trainCards.size();
            }

            @Override
            public TrainCard next() {
                return TrainCardSet.this.trainCards.get(index++);
            }
        };
    }

}
