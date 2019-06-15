package shared.model.interfaces;

import java.awt.Point;

/**
 * Created by paulinecausse on 2/28/18.
 */

public interface IBlock {
    void setPoint(Point point);

    void setId(int id);

    Point getPoint();

    int getId();
}
