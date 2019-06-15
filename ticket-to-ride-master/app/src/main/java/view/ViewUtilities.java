package view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.a340team.tickettoride.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ClientRoot;
import shared.model.DestCard;
import static shared.model.initialized_info.DestCardId.*;


/**
 *
 * Created by BenNelson on 2/7/18.
 *
 * The point of this class is to hold certain methods that all or most views will use.
 */

public class ViewUtilities {

    static String GAME_START = "gameStart";
    static boolean containsSpecialCharacters(String str){

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            //Contains special characters
            return true;
        }

        //Doesn't contain special characters
        return false;
    }

    static void displayMessage(String message, AppCompatActivity activity) {
        //Just pop up a toast letting the user know what happened
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(activity, message, duration);
        toast.show();
    }

    static void displayMessage(String message, Context context) {
        //Just pop up a toast letting the user know what happened
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    static ArrayList<Integer> createDestCardImagePaths(List<DestCard> destCards) {
        ArrayList<Integer> imagePathList = new ArrayList<>();
        for (DestCard card : destCards) {
            int path = _findImagePath(card.getDestination(), card.getStartingPoint());
            imagePathList.add(path);
        }
        return imagePathList;
    }

    private static int _findImagePath(String end, String start) {

        //Destination - Start
        if(end.equals(NEW_YORK) && start.equals(LOS_ANGELES)){
            return R.drawable.lax_nyc;
        }
        else if(end.equals(HOUSTON) && start.equals(DULUTH)){
            return R.drawable.dlh_hou;
        }
        else if(end.equals(NASHVILLE) && start.equals(SAUL_ST_MARIE)){
            return R.drawable.ssm_nash;
        }
        else if(end.equals(ATLANTA) && start.equals(NEW_YORK)){
            return R.drawable.nyc_atl;
        }
        else if(end.equals(NASHVILLE) && start.equals(PORTLAND)){
            return R.drawable.pdx_nash;
        }
        else if(end.equals(MONTREAL) && start.equals(VANCOUVER)){
            return R.drawable.van_mon;
        }
        else if(end.equals(EL_PASO) && start.equals(DULUTH)){
            return R.drawable.dlh_elp;
        }
        else if(end.equals(MIAMI) && start.equals(TORONTO)){
            return R.drawable.tor_mia;
        }
        else if(end.equals(PHOENIX) && start.equals(PORTLAND)){
            return R.drawable.pdx_phx;
        }
        else if(end.equals(NEW_YORK) && start.equals(DALLAS)){
            return R.drawable.dal_nyc;
        }
        else if(end.equals(SALT_LAKE_CITY) && start.equals(CALGARY)){
            return R.drawable.cal_slc;
        }
        else if(end.equals(PHOENIX) && start.equals(CALGARY)){
            return R.drawable.cal_phx;
        }
        else if(end.equals(MIAMI) && start.equals(LOS_ANGELES)){
            return R.drawable.lax_mia;
        }
        else if(end.equals(LITTLE_ROCK) && start.equals(WINNIPEG)){
            return R.drawable.win_lrk;
        }
        else if(end.equals(ATLANTA) && start.equals(SAN_FRANCISCO)){
            return R.drawable.sfa_atl;
        }
        else if(end.equals(HOUSTON) && start.equals(KANSAS_CITY)){
            return R.drawable.kan_hou;
        }
        else if(end.equals(CHICAGO) && start.equals(LOS_ANGELES)){
            return R.drawable.lax_chi;
        }
        else if(end.equals(PITTSBURGH) && start.equals(DENVER)){
            return R.drawable.den_pit;
        }
        else if(end.equals(SANTA_FE) && start.equals(CHICAGO)){
            return R.drawable.chi_saf;
        }
        else if(end.equals(SANTA_FE) && start.equals(VANCOUVER)){
            return R.drawable.van_sfa;
        }
        else if(end.equals(MIAMI) && start.equals(BOSTON)){
            return R.drawable.bos_mia;
        }
        else if(end.equals(NEW_ORLEANS) && start.equals(CHICAGO)){
            return R.drawable.chi_nola;
        }
        else if(end.equals(ATLANTA) && start.equals(MONTREAL)){
            return R.drawable.mon_atl;
        }
        else if(end.equals(NEW_YORK) && start.equals(SEATTLE)){
            return R.drawable.sea_nyc;
        }
        else if(end.equals(EL_PASO) && start.equals(DENVER)){
            return R.drawable.den_elp;
        }
        else if(end.equals(LOS_ANGELES) && start.equals(HELENA)){
            return R.drawable.hln_lax;
        }
        else if(end.equals(HOUSTON) && start.equals(WINNIPEG)){
            return R.drawable.win_hou;
        }
        else if(end.equals(NEW_ORLEANS) && start.equals(MONTREAL)){
            return R.drawable.mon_nola;
        }
        else if(end.equals(OKLAHOMA_CITY) && start.equals(SAUL_ST_MARIE)){
            return R.drawable.ssm_okc;
        }
        else if(end.equals(LOS_ANGELES) && start.equals(SEATTLE)){
            return R.drawable.sea_lax;
        }
        else
        {
            //If you get a train card that means there is a failure
            return R.drawable.black;
        }
    }

    public static void checkServerStatus(AppCompatActivity appCompatActivity){

        if(ClientRoot.isServerDown()){
            if (!ClientRoot.isServerDownActivityUp()){
                Intent intent = new Intent(appCompatActivity, ServerDownActivity.class);
                appCompatActivity.startActivity(intent);
            }

        }
    }
}
