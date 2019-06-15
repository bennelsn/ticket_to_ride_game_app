package card_algorithms;

import java.util.List;
import java.util.Random;

import shared.model.Train;
import shared.model.TrainCard;

/**
 * Created by paulinecausse on 3/3/18.
 */

public class CardShuffler {
    public static void shuffleList(List<TrainCard> trainCards) {
        //TODO I think we could put this into the deck class. and I think that we could just replace it with Collections.shuffle()
        int size = trainCards.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < size; i++) {
            int change = i + random.nextInt(size - i);
            swap(trainCards, i, change);
        }
    }

    private static void swap(List<TrainCard> trainCards, int i, int change) {
        TrainCard helper = trainCards.get(i);
        trainCards.set(i, trainCards.get(change));
        trainCards.set(change, helper);
    }
}
