package test;

import java.util.List;

import card_algorithms.CardShuffler;
import shared.logging.Logger;
import shared.model.TrainCard;
import shared.model.initialized_info.TrainCards;

/**
 * Created by paulinecausse on 3/3/18.
 */

public class CardsShuffleTest {
    public CardsShuffleTest() {}

    public static void main(String[] args){
        List<TrainCard> trainCards = TrainCards.instance().get_trainCards();

        Logger.log("BEFORE: ");
        for (int i = 0; i < trainCards.size(); i++){
            Logger.log(trainCards.get(i).getColor() + ", ");
        }

        Logger.log("\n");

        Logger.log("AFTER: ");
         CardShuffler.shuffleList(trainCards);
        for (int i = 0; i < trainCards.size(); i++){
            Logger.log(trainCards.get(i).getColor() + ", ");
        }
    }
}
