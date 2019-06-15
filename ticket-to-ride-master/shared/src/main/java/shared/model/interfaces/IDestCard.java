package shared.model.interfaces;

import shared.model.interfaces.Card;

/**
 * Created by paulinecausse on 2/28/18.
 */

public interface IDestCard extends Card {
    boolean isCompleted();

    void setCompleted(boolean completed);

    int getPoints();

    void setPoints(int pointAmt);

    String getDestination();

    void setDestination(String destination);

    String getStartingPoint();

    void setStartingPoint(String startingPoint);

}
