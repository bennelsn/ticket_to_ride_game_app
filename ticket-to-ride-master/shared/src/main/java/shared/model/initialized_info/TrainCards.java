package shared.model.initialized_info;

import org.omg.IOP.TAG_INTERNET_IOP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.model.Color;
import shared.model.Train;
import shared.model.TrainCard;

/**
 * Created by paulinecausse on 2/27/18.
 */

// 12 of each except for the locomotive (14)

public class TrainCards implements Serializable{
    private static TrainCards _instance = new TrainCards();

    public static TrainCards instance() {

        if (_instance == null)
            _instance = new TrainCards();
        return _instance;
    }

    public static List<TrainCard> getTrainCards(){
        return _instance._trainCards;
    }

    private TrainCards(){
        this._trainCards = new ArrayList<TrainCard>();
        set_trainCards();
    }

    private List<TrainCard> _trainCards;

    public List<TrainCard> get_trainCards(){
        return _trainCards;
    }


    private void set_trainCards(){
        for (int i = 0; i < 14; i++){
            _trainCards.add(new TrainCard(Color.RAINBOW, 2));
        }

        for (int i = 0; i < 12; i++) {
            _trainCards.add(new TrainCard(Color.PINK, 1));
            _trainCards.add(new TrainCard(Color.RED, 1));
            _trainCards.add(new TrainCard(Color.BLUE, 1));
            _trainCards.add(new TrainCard(Color.ORANGE, 1));
            _trainCards.add(new TrainCard(Color.WHITE, 1));
            _trainCards.add(new TrainCard(Color.BLACK, 1));
            _trainCards.add(new TrainCard(Color.GREEN, 1));
            _trainCards.add(new TrainCard(Color.YELLOW, 1));
        }
    }
}
