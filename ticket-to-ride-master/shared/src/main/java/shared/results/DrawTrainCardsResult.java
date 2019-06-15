package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.TrainCard;

/**
 * Created by bdemann on 3/20/18.
 */

public class DrawTrainCardsResult extends Result {

    private List<TrainCard> faceUpCards;
    private TrainCard drawnCard;

    public DrawTrainCardsResult(TrainCard drawnCard, List<TrainCard> faceUpCards, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this.drawnCard = drawnCard;
        this.faceUpCards = faceUpCards;
    }

    public DrawTrainCardsResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public DrawTrainCardsResult(List<ICommand> clientCommands, String userMessage) {
        super(false, clientCommands, userMessage);
    }

    public TrainCard getDrawnCard() {
        return drawnCard;
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }
}
