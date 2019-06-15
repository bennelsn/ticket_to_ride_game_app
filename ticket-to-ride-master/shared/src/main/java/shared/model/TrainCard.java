package shared.model;

import java.io.Serializable;

import shared.model.interfaces.ITrainCard;

/**
 * Created by BenNelson on 2/2/18.
 */
public class TrainCard implements ITrainCard, Serializable {
    private Color _color;
    private int _actionWeight;

    public TrainCard(Color color,int actionWeight) {
        this._color = color;
        this._actionWeight = actionWeight;
    }

    public TrainCard(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainCard trainCard = (TrainCard) o;

        return _color.equals(trainCard._color);
    }

    @Override
    public int hashCode() {
        return _color.hashCode();
    }

    @Override
    public void setColor(Color color){
        this._color = color;
    }

    @Override
    public Color getColor(){
        return _color;
    }

    @Override
    public void setActionWeight(int actionWeight){
        this._actionWeight = actionWeight;
    }

    @Override
    public int getActionWeight(){
        return _actionWeight;
    }

    @Override
    public String toString() {
        return _color.toString();
    }
}
