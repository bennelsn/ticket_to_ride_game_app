package shared.model;

import java.util.ArrayList;
import java.util.List;

import shared.model.interfaces.IGraph;
import shared.model.interfaces.IRoute;

/**
 * Created by paulinecausse on 3/24/18.
 */

public class Graph implements IGraph{
    //list of a player's routes
    private List<IRoute> edges;
    //cities that are part of a player's routes
    //each city has a list of out-going edges
    private List<City> vertices;
    private String ownerUsername;

    public Graph(List<IRoute> edges, List<City> vertex, String username){
        this.edges = edges;
        this.vertices = vertex;
        this.ownerUsername = username;

        setOutGoingEdged();
    }

    @Override
    public void setOutGoingEdged(){
        for(City city:vertices){
            String cityName = city.get_name();
            for(IRoute edge:edges){
                if(edge.getStart().get_name().equals(cityName)){
                    city.addOutGoingEdge(edge);
                }
            }
        }
    }

    @Override
    public List<City> getVertices(){
        return vertices;
    }

    @Override
    public List<IRoute> getEdges(){
        return edges;
    }

}
