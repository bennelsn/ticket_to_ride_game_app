package shared.model;

import java.awt.Point;
import java.io.Serializable;

import shared.model.interfaces.IBlock;

/**
 * Created by paulinecausse on 2/28/18.
 */

public class Block implements IBlock, Serializable {
    private Point _point;
    private int _id;

    public Block(Point point, int id){
        this._id = id;
        this._point = point;
    }

    public void setPoint(Point point){ this._point = point; }

    public void setId(int id){ this._id = id; }

    public Point getPoint(){ return _point; }

    public int getId(){ return _id; }
}
