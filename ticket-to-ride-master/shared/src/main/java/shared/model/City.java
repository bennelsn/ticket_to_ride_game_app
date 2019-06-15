package shared.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.model.interfaces.IRoute;

/**
 * Created by paulinecausse on 3/2/18.
 */

public class City implements Serializable {
    private CityPoint _coordinates;
    private String _name;
    private boolean isVisited;
    private List<IRoute> outgoingEdges;

    public City(CityPoint coordinates, String name){
        this._coordinates = coordinates;
        this._name = name;
        this.outgoingEdges = new ArrayList<>();
    }

    public void setVisited(boolean isVisited){
        this.isVisited = isVisited;
    }

    public boolean isVisited(){
        return isVisited;
    }

    public void addOutGoingEdge(IRoute route){
        outgoingEdges.add(route);
    }

    public List<IRoute> getOutGoingEdges(){
        return outgoingEdges;
    }

    public CityPoint get_coordinates() {
        return _coordinates;
    }

    public String get_name() {
        return _name;
    }

}
