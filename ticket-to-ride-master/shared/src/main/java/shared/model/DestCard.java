package shared.model;

import java.io.Serializable;

import shared.model.interfaces.IDestCard;

/**
 * Created by BenNelson on 2/2/18.
 *
 * Destination _cards give or take away points as indicated by the number on the card.
 * Add points to a player's score if the route is successfully claimed by the player.
 *
 * The card has a (unique?) route from one city to another.
 * The card also has a point amount.
 */

public class DestCard implements IDestCard, Serializable {
    private String _destination;
    private String _startingPoint;
    private boolean _completed;
    private int _points;

    public String toString(){
        return "Routes: " + _startingPoint + " to " + _destination;
    }

//    public DestCard(){
//        DestinationCards.instance();
//    }

    public DestCard(String destination, String startingPoint, int points){
        this._destination = destination;
        this._startingPoint = startingPoint;
        this._points = points;
    }

    @Override
    public boolean isCompleted() {
        return _completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this._completed = completed;
    }

    @Override
    public int getPoints() {
        return _points;
    }

    @Override
    public void setPoints(int pointAmt) {
        this._points = pointAmt;
    }

    @Override
    public String getDestination() {
        return _destination;
    }

    @Override
    public void setDestination(String _destination) {
        this._destination = _destination;
    }

    @Override
    public String getStartingPoint() {
        return _startingPoint;
    }

    @Override
    public void setStartingPoint(String _startingPoint) {
        this._startingPoint = _startingPoint;
    }

}
