package server.facades.helpers;

import java.util.List;

import shared.model.City;
import shared.model.Route;
import shared.model.interfaces.IRoute;

/**
 * Created by paulinecausse on 3/24/18.
 */

public class LongestPath {
    static int times;

    public int initLongestPath(List<City> vertices, City source) {
        for (City city : vertices) {
            city.setVisited(false);
        }
        return getLongestPath(source);
    }

    public int getLongestPath(City currentCity) {
        ++times;
        System.out.println(times);
        int dist, max = 0;
        currentCity.setVisited(true);
        for (IRoute edge : currentCity.getOutGoingEdges()) {
            if (!edge.getEnd().isVisited()) {
                dist = edge.getLength() + getLongestPath(edge.getEnd());
                if (dist > max)
                    max = dist;
            }
        }

        currentCity.setVisited(false);
        return max;
    }
}
