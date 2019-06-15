package shared.model;

import java.io.Serializable;

/**
 * We had to create our own custom point.
 * Created by BenNelson on 3/8/18.
 */
public class CityPoint implements Serializable{

    private int _x;
    private int _y;

    public CityPoint(int i, int i1) {
        this._x = i;
        this._y = i1;
    }

    public int x(){
        return this._x;
    }
    public int y(){
        return this._y;
    }

}
