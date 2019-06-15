package shared.results;

import java.util.List;

import shared.command.ICommand;
import shared.model.DestCard;
import shared.model.interfaces.Card;

/**
 * Created by bdemann on 3/3/18.
 */

public class DrawDestCardsResult extends Result {

    List<DestCard> _cards;

    public DrawDestCardsResult(List<DestCard> cards, boolean success, List<ICommand> clientCommands, String userMessage) {
        super(success, clientCommands, userMessage);
        this._cards = cards;
    }

    public DrawDestCardsResult(String exceptionType, String exceptionMessage) {
        super(exceptionType, exceptionMessage);
    }

    public List<DestCard> getCards() {
        return _cards;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Draw Card Result - ");
        sb.append("Success: " + _success);
        sb.append("\n");
        sb.append("Drawn Cards: \n");
        for(Card card : _cards) {
            sb.append(card);
            sb.append("\t");
        }
        return sb.toString();
    }
}
