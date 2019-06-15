package shared.model.interfaces;

import java.util.List;

import shared.model.City;
import shared.model.Route;

/**
 * Created by paulinecausse on 3/24/18.
 */

public interface IGraph {
    void setOutGoingEdged();
    List<City> getVertices();
    List<IRoute> getEdges();
}
