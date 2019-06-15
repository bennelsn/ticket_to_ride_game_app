package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.a340team.tickettoride.R;

import java.util.List;
import java.util.Map;

import shared.model.CityPoint;
import shared.model.Route;
import shared.model.interfaces.IRoute;

import static shared.model.Color.BLACK;
import static shared.model.Color.BLUE;
import static shared.model.Color.GRAY;
import static shared.model.Color.GREEN;
import static shared.model.Color.ORANGE;
import static shared.model.Color.PINK;
import static shared.model.Color.RED;
import static shared.model.Color.WHITE;
import static shared.model.Color.YELLOW;

/**
 *
 * Created by BenNelson on 3/8/18.
 */

public class DrawUtilities extends View {

    private Context _context;
    private static double scaleFactor = 0.665;

    public DrawUtilities(Context context) {
        super(context);
        this._context = context;

    }

    public void drawRoutes(Map<shared.model.Color, List<IRoute>> routesMap, ImageView view) {
        //super.onDraw(canvas);
        //Set the paint
        Paint paint = new Paint();
        paint.setStrokeWidth(8);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float screenScale = _context.getResources().getDisplayMetrics().density;

        //Set the bit map
        Bitmap gameMapBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ticket_to_ride_map_with_routes);
        Bitmap drawableBitmap = gameMapBitmap.copy(Bitmap.Config.ARGB_8888, true);

        //Get the final canvas
        Canvas canvas = new Canvas(drawableBitmap);

        for(shared.model.Color playerColor : routesMap.keySet()){
            if(routesMap.containsKey(playerColor)){
                List<IRoute> routes = routesMap.get(playerColor);

                for (IRoute route : routes) {
                    CityPoint start = _scale(route.getStart().get_coordinates(), scaleFactor, screenScale);
                    CityPoint end = _scale(route.getEnd().get_coordinates(), scaleFactor, screenScale);
                    paint.setColor(_convertColor(playerColor));
                    canvas.drawLine(start.x(),start.y(),end.x(),end.y(),paint);
                }
            }
        }

        //Set the new bg image with the strokes
        view.setImageBitmap(drawableBitmap);
    }

    private CityPoint _scale(CityPoint coords, double scaleFactor, float screenScale) {
        double x = coords.x() * screenScale;
        double y = coords.y() * screenScale;
        x *= scaleFactor;
        y *= scaleFactor;
        return new CityPoint((int)x,(int)y);
    }

    /*
    This method converts our shared model colors to android graphics colors
     */
    private int _convertColor(shared.model.Color color) {
        if(color.equals(PINK)){
            return Color.MAGENTA;
        }
        if(color.equals(RED)){
            return Color.RED;
        }
        if(color.equals(BLUE)){
            return Color.BLUE;
        }
        if(color.equals(ORANGE)){
            return Color.rgb(255,165,0);
        }
        if(color.equals(WHITE)){
            return Color.WHITE;
        }
        if(color.equals(BLACK)){
            return Color.BLACK;
        }
        if(color.equals(GREEN)){
            return Color.GREEN;
        }
        if(color.equals(YELLOW)){
            return Color.YELLOW;
        }
        if(color.equals(GRAY)){
            return Color.GRAY;
        }
        return Color.CYAN;//CYAN MEANS ERROR
    }

}
