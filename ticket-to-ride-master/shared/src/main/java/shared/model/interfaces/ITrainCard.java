package shared.model.interfaces;

import shared.model.Color;
import shared.model.interfaces.Card;

/**
 * Created by paulinecausse on 2/28/18.
 */

public interface ITrainCard extends Card {
    void setColor(Color color);

    void setActionWeight(int actionWeight);

    int getActionWeight();

    Color getColor();
}
