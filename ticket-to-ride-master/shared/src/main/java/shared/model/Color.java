package shared.model;

import java.io.Serializable;

/**
 * Created by bdemann on 3/4/18.
 */

public class Color implements Serializable{

    //I think I accidentally made an enum.... but maybe if we decide to actually colors on these guys we will be glad to have the option...

    public static final Color RAINBOW = new Color("rainbow");
    public static final Color PINK = new Color("pink");
    public static final Color RED = new Color("red");
    public static final Color BLUE = new Color("blue");
    public static final Color ORANGE = new Color("orange");
    public static final Color WHITE = new Color("white");
    public static final Color BLACK = new Color("black");
    public static final Color GREEN = new Color("green");
    public static final Color YELLOW = new Color("yellow");
    public static final Color GRAY = new Color("gray");
    public static final int PLAYER_RED = -65536;
    public static final int PLAYER_YELLOW = -256;
    public static final int PLAYER_BLACK = -16777216;
    public static final int PLAYER_BLUE = -16776961;
    public static final int PLAYER_GREEN = -16711936;

    private String _color;

    private Color(String color) {
        this._color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Color) {
            Color c = (Color) o;
            return _color.equals(c._color);
        }
        return false;
    }

    @Override
    public String toString() {
        return _color;
    }
}
