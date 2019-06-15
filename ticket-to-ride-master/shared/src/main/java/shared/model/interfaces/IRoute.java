package shared.model.interfaces;

import shared.model.City;
import shared.model.Color;

/**
 * Created by paulinecausse on 2/28/18.
 */

public interface IRoute {
    void setLength(int length);

    void setStart(City start);

    void setEnd(City end);

    void setColor(Color color);

    int getLength();

    City getStart();

    City getEnd();

    Color getColor();

    int getValue();

    void setOwner(String username);

    String getOwner();
}
