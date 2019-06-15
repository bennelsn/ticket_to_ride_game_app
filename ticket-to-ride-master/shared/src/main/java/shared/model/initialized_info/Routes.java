package shared.model.initialized_info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.model.City;
import shared.model.CityPoint;
import shared.model.Color;
import shared.model.Route;
import shared.model.interfaces.IRoute;

/**
 *
 * Created by paulinecausse on 3/2/18.
 */

public class Routes implements Serializable {
    private static Routes _instance;
    private Cities cities = Cities.instance();
    private Map<String , IRoute> _routesMap;
    private List<String> _routesList;

    public static String _1 = "1";
    public static String _1b = "1b";
    public static String _2 = "2";
    public static String _2b = "2b";
    public static String _3 = "3";
    public static String _4 = "4";
    public static String _5 = "5";
    public static String _6 = "6";
    public static String _7 = "7";
    public static String _8 = "8";
    public static String _9 = "9";
    public static String _10 = "10";
    public static String _10b = "10b";
    public static String _11 = "11";
    public static String _11b = "11b";
    public static String _12 = "12";
    public static String _12b = "12b";
    public static String _13 = "13";
    public static String _14 = "14";
    public static String _15 = "15";
    public static String _16 = "16";
    public static String _17 = "17";
    public static String _17b = "17b";
    public static String _18 = "18";
    public static String _19 = "19";
    public static String _20 = "20";
    public static String _21 = "21";
    public static String _22 = "22";
    public static String _23 = "23";
    public static String _24 = "24";
    public static String _25 = "25";
    public static String _26 = "26";
    public static String _26b = "26b";
    public static String _27 = "27";
    public static String _28 = "28";
    public static String _28b = "28b";
    public static String _29 = "29";
    public static String _30 = "30";
    public static String _31 = "31";
    public static String _32 = "32";
    public static String _33 = "33";
    public static String _34 = "34";
    public static String _34b = "34b";
    public static String _35 = "35";
    public static String _36 = "36";
    public static String _36b = "36b";
    public static String _37 = "37";
    public static String _38 = "38";
    public static String _39 = "39";
    public static String _39b = "39b";
    public static String _40 = "40";
    public static String _41 = "41";
    public static String _41b = "41b";
    public static String _42 = "42";
    public static String _43 = "43";
    public static String _43b = "43b";
    public static String _44 = "44";
    public static String _44b = "44b";
    public static String _45 = "45";
    public static String _46 = "46";
    public static String _47 = "47";
    public static String _48 = "48";
    public static String _49 = "49";
    public static String _50 = "50";
    public static String _51 = "51";
    public static String _52 = "52";
    public static String _52b = "52b";
    public static String _53 = "53";
    public static String _54 = "54";
    public static String _55 = "55";
    public static String _56 = "56";
    public static String _57 = "57";
    public static String _58 = "58";
    public static String _58b = "58b";
    public static String _59 = "59";
    public static String _59b = "59b";
    public static String _60 = "60";
    public static String _61 = "61";
    public static String _62 = "62";
    public static String _62b = "62b";
    public static String _63 = "63";
    public static String _63b = "63b";
    public static String _64 = "64";
    public static String _64b = "64b";
    public static String _65 = "65";
    public static String _65b = "65b";
    public static String _66 = "66";
    public static String _67 = "67";
    public static String _68 = "68";
    public static String _69 = "69";
    public static String _70 = "70";
    public static String _71 = "71";
    public static String _72 = "72";
    public static String _72b = "72b";
    public static String _73 = "73";
    public static String _74 = "74";
    public static String _75 = "75";
    public static String _76 = "76";
    public static String _77 = "77";

    public static Routes instance() {

        if (_instance == null)
            _instance = new Routes();
        return _instance;
    }

    private Routes(){
        this._routesMap = new HashMap<>();
        this._routesList = new ArrayList<>();
        setRoutes();
    }

    private void setRoutes(){

        this._routesMap.put(_1, new Route(1, cities.getVanc(), cities.getSeat(), Color.GRAY, false));
        this._routesMap.put(_1b, new Route(1, cities.getVancOFFSET(), cities.getSeatOFFSET(), Color.GRAY, false));
        this._routesMap.put(_2, new Route(1, cities.getSeatOFFSET(), cities.getPortOFFSET(), Color.GRAY,false));
        this._routesMap.put(_2b, new Route(1, cities.getSeat(), cities.getPort(), Color.GRAY,false));
        this._routesMap.put(_3, new Route(4, cities.getSeat(), cities.getCal(), Color.GRAY, false));
        this._routesMap.put(_4, new Route(3, cities.getVanc(), cities.getCal(), Color.GRAY, false));
        this._routesMap.put(_5, new Route(6, cities.getCal(), cities.getWinn(), Color.WHITE, false));
        this._routesMap.put(_6, new Route(4, cities.getCal(), cities.getHel(), Color.GRAY, false));
        this._routesMap.put(_7, new Route(6, cities.getSeat(), cities.getHel(), Color.YELLOW, false));
        this._routesMap.put(_8, new Route(4, cities.getWinn(), cities.getHel(), Color.BLUE, false));
        this._routesMap.put(_9, new Route(6, cities.getPort(), cities.getSlc(), Color.BLUE, false));
        this._routesMap.put(_10, new Route(5, cities.getPortOFFSET(), cities.getSanFrOFFSET(), Color.GREEN, false));
        this._routesMap.put(_10b, new Route(5, cities.getPort(), cities.getSanFr(), Color.PINK, false));
        this._routesMap.put(_11, new Route(5, cities.getSanFr(), cities.getSlc(), Color.ORANGE, false));
        this._routesMap.put(_11b, new Route(5, cities.getSanFrOFFSET(), cities.getSlcOFFSET(), Color.WHITE, false));
        this._routesMap.put(_12, new Route(3, cities.getSanFr(), cities.getLax(), Color.PINK, false));
        this._routesMap.put(_12b, new Route(3, cities.getSanFrOFFSET(), cities.getLaxOFFSET(), Color.YELLOW, false));
        this._routesMap.put(_13, new Route(2, cities.getLax(), cities.getLasV(), Color.GRAY, false));
        this._routesMap.put(_14, new Route(3, cities.getLax(), cities.getPhx(), Color.GRAY, false));
        this._routesMap.put(_15, new Route(6, cities.getLax(), cities.getElP(), Color.BLACK, false));
        this._routesMap.put(_16, new Route(3, cities.getLasV(), cities.getSlc(), Color.ORANGE, false));
        this._routesMap.put(_17, new Route(3, cities.getSlc(), cities.getDvr(), Color.RED, false));
        this._routesMap.put(_17b, new Route(3, cities.getSlcOFFSET(), cities.getDvrOFFSET(), Color.YELLOW, false));
        this._routesMap.put(_18, new Route(5, cities.getPhx(), cities.getDvr(), Color.WHITE, false));
        this._routesMap.put(_19, new Route(3, cities.getPhx(), cities.getSanFe(), Color.GRAY, false));
        this._routesMap.put(_20 , new Route(3, cities.getPhx(), cities.getElP(), Color.GRAY, false));
        this._routesMap.put(_21 , new Route(6, cities.getElP(), cities.getHou(), Color.GREEN, false));
        this._routesMap.put(_22 , new Route(2, cities.getElP(), cities.getSanFe(), Color.GRAY, false));
        this._routesMap.put(_23 , new Route(5, cities.getElP(), cities.getOmhC(), Color.YELLOW, false));
        this._routesMap.put(_24 , new Route(4, cities.getElP(), cities.getDal(), Color.RED, false));
        this._routesMap.put(_25 , new Route(2, cities.getHou(), cities.getNewO(), Color.GRAY, false));
        this._routesMap.put(_26 , new Route(1, cities.getHouOFFSET(), cities.getDalOFFSET(), Color.GRAY, false));
        this._routesMap.put(_26b, new Route(1, cities.getHou(), cities.getDal(), Color.GRAY, false));
        this._routesMap.put(_27 , new Route(3, cities.getNewO(), cities.getlRock(), Color.GREEN, false));
        this._routesMap.put(_28 , new Route(4, cities.getNewO(), cities.getAtl(), Color.ORANGE, false));
        this._routesMap.put(_28b , new Route(4, cities.getNewOOFFSET(), cities.getAtlOFFSET(), Color.YELLOW, false));
        this._routesMap.put(_29 , new Route(6, cities.getNewO(), cities.getMiami(), Color.RED, false));
        this._routesMap.put(_30 , new Route(5, cities.getMiami(), cities.getAtl(), Color.BLUE, false));
        this._routesMap.put(_31 , new Route(4, cities.getMiami(), cities.getCharl(), Color.PINK, false));
        this._routesMap.put(_32 , new Route(2, cities.getCharl(), cities.getAtl(), Color.GRAY, false));
        this._routesMap.put(_33 , new Route(2, cities.getCharl(), cities.getRal(), Color.GRAY, false));
        this._routesMap.put(_34 , new Route(2, cities.getAtlOFFSET(), cities.getRalOFFSET(), Color.GRAY, false));
        this._routesMap.put(_34b , new Route(2, cities.getAtl(), cities.getRal(), Color.GRAY, false));
        this._routesMap.put(_35 , new Route(1, cities.getAtl(), cities.getNash(), Color.GRAY, false));
        this._routesMap.put(_36 , new Route(2, cities.getRalOFFSET(), cities.getWashOFFSET(), Color.GRAY, false));
        this._routesMap.put(_36b , new Route(2, cities.getRal(), cities.getWash(), Color.GRAY, false));
        this._routesMap.put(_37 , new Route(3, cities.getRal(), cities.getNash(), Color.BLACK, false));
        this._routesMap.put(_38 , new Route(2, cities.getRal(), cities.getPitt(), Color.GRAY, false));
        this._routesMap.put(_39 , new Route(2, cities.getWashOFFSET(), cities.getNyc(), Color.ORANGE, false));
        this._routesMap.put(_39b , new Route(2, cities.getWash(), cities.getNyc(), Color.BLACK, false));
        this._routesMap.put(_40 , new Route(2, cities.getWash(), cities.getPitt(), Color.GRAY, false));
        this._routesMap.put(_41 ,new Route(2, cities.getNyc(), cities.getBost(), Color.RED, false));
        this._routesMap.put(_41b , new Route(2, cities.getNycOFFSET(), cities.getBostOFFSET(), Color.YELLOW, false));
        this._routesMap.put(_42 , new Route(3, cities.getNyc(), cities.getMont(), Color.BLUE, false));
        this._routesMap.put(_43 , new Route(2, cities.getNyc(), cities.getPitt(), Color.WHITE, false));
        this._routesMap.put(_43b , new Route(2, cities.getNyc(), cities.getPitt(), Color.GREEN, false));
        this._routesMap.put(_44 , new Route(2, cities.getBostOFFSET(), cities.getMontOFFSET(), Color.GRAY, false));
        this._routesMap.put(_44b , new Route(2, cities.getBost(), cities.getMont(), Color.GRAY, false));
        this._routesMap.put(_45 , new Route(5, cities.getMont(), cities.getStm(), Color.BLACK, false));
        this._routesMap.put(_46 , new Route(3, cities.getMont(), cities.getTor(), Color.GRAY, false));
        this._routesMap.put(_47 , new Route(2, cities.getTor(), cities.getPitt(), Color.GRAY, false));
        this._routesMap.put(_48 , new Route(2, cities.getTor(), cities.getStm(), Color.GRAY, false));
        this._routesMap.put(_49 , new Route(4, cities.getTor(), cities.getChic(), Color.WHITE, false));
        this._routesMap.put(_50 , new Route(6, cities.getTor(), cities.getDvr(), Color.PINK, false));
        this._routesMap.put(_51 , new Route(4, cities.getPitt(), cities.getNash(), Color.YELLOW, false));
        this._routesMap.put(_52 , new Route(3, cities.getPitt(), cities.getChic(), Color.ORANGE, false));
        this._routesMap.put(_52b , new Route(3, cities.getPitt(), cities.getChic(), Color.BLACK, false));
        this._routesMap.put(_53 , new Route(3, cities.getNash(), cities.getlRock(), Color.WHITE, false));
        this._routesMap.put(_54 , new Route(2, cities.getNash(), cities.getStL(), Color.GRAY, false));
        this._routesMap.put(_55 , new Route(2, cities.getlRock(), cities.getDal(), Color.GRAY, false));
        this._routesMap.put(_56 , new Route(2, cities.getlRock(), cities.getOmhC(), Color.GRAY, false));
        this._routesMap.put(_57 , new Route(2, cities.getlRock(), cities.getStL(), Color.GRAY, false));
        this._routesMap.put(_58 , new Route(2, cities.getStL(), cities.getChic(), Color.WHITE, false));
        this._routesMap.put(_58b , new Route(2, cities.getStL(), cities.getChic(), Color.GREEN, false));
        this._routesMap.put(_59 , new Route(2, cities.getStL(), cities.getKanC(), Color.PINK, false));
        this._routesMap.put(_59b , new Route(2, cities.getStL(), cities.getKanC(), Color.BLUE, false));
        this._routesMap.put(_60 , new Route(4, cities.getChic(), cities.getOmh(), Color.BLUE, false));
        this._routesMap.put(_61 , new Route(3, cities.getChic(), cities.getDul(), Color.RED, false));
        this._routesMap.put(_62 , new Route(2, cities.getDalOFFSET(), cities.getOmhCOFFSET(), Color.GRAY, false));
        this._routesMap.put(_62b , new Route(2, cities.getDal(), cities.getOmhC(), Color.GRAY, false));
        this._routesMap.put(_63 , new Route(2, cities.getOmhCOFFSET(), cities.getKanC(), Color.GRAY, false));
        this._routesMap.put(_63b , new Route(2, cities.getOmhC(), cities.getKanC(), Color.GRAY, false));
        this._routesMap.put(_64 , new Route(1, cities.getKanC(), cities.getOmhOFFSET(), Color.GRAY, false));
        this._routesMap.put(_64b , new Route(1, cities.getKanC(), cities.getOmh(), Color.GRAY, false));
        this._routesMap.put(_65 , new Route(2, cities.getOmhOFFSET(), cities.getDulOFFSET(), Color.GRAY, false));
        this._routesMap.put(_65b , new Route(2, cities.getOmh(), cities.getDul(), Color.GRAY, false));
        this._routesMap.put(_66 , new Route(3, cities.getDul(), cities.getStm(), Color.GRAY, false));
        this._routesMap.put(_67 , new Route(6, cities.getWinn(), cities.getStm(), Color.GRAY, false));
        this._routesMap.put(_68 , new Route(4, cities.getDul(), cities.getWinn(), Color.BLACK, false));
        this._routesMap.put(_69 , new Route(6, cities.getDul(), cities.getHel(), Color.ORANGE, false));
        this._routesMap.put(_70 , new Route(5, cities.getOmh(), cities.getHel(), Color.RED, false));
        this._routesMap.put(_71 , new Route(4, cities.getOmh(), cities.getDvr(), Color.PINK, false));
        this._routesMap.put(_72 , new Route(4, cities.getKanC(), cities.getDvrOFFSET(), Color.ORANGE, false));
        this._routesMap.put(_72b , new Route(4, cities.getKanCOFFSET(), cities.getDvr(), Color.BLACK, false));
        this._routesMap.put(_73 , new Route(4, cities.getDvr(), cities.getOmhC(), Color.RED, false));
        this._routesMap.put(_74 , new Route(3, cities.getOmhC(), cities.getSanFe(), Color.BLUE, false));
        this._routesMap.put(_75 , new Route(2, cities.getSanFe(), cities.getDvr(), Color.GRAY, false));
        this._routesMap.put(_76 , new Route(4, cities.getDvr(), cities.getHel(), Color.GREEN, false));
        this._routesMap.put(_77 , new Route(3, cities.getSlc(), cities.getHel(), Color.PINK, false));



        _routesList.add(_1);
        _routesList.add(_1b);
        _routesList.add(_2);
        _routesList.add(_2b);
        _routesList.add(_3);
        _routesList.add(_4);
        _routesList.add(_5);
        _routesList.add(_6);
        _routesList.add(_7);
        _routesList.add(_8);
        _routesList.add(_9);
        _routesList.add(_10);
        _routesList.add(_10b);
        _routesList.add(_11);
        _routesList.add(_11b);
        _routesList.add(_12);
        _routesList.add(_12b);
        _routesList.add(_13);
        _routesList.add(_14);
        _routesList.add(_15);
        _routesList.add(_16);
        _routesList.add(_17);
        _routesList.add(_17b);
        _routesList.add(_18);
        _routesList.add(_19);
        _routesList.add(_20);
        _routesList.add(_21);
        _routesList.add(_22);
        _routesList.add(_23);
        _routesList.add(_24);
        _routesList.add(_25);
        _routesList.add(_26);
        _routesList.add(_26b);
        _routesList.add(_27);
        _routesList.add(_28);
        _routesList.add(_28b);
        _routesList.add(_29);
        _routesList.add(_30);
        _routesList.add(_31);
        _routesList.add(_32);
        _routesList.add(_33);
        _routesList.add(_34);
        _routesList.add(_34b);
        _routesList.add(_34);
        _routesList.add(_35);
        _routesList.add(_36);
        _routesList.add(_36b);
        _routesList.add(_37);
        _routesList.add(_38);
        _routesList.add(_39);
        _routesList.add(_39b);
        _routesList.add(_40);
        _routesList.add(_41);
        _routesList.add(_41b);
        _routesList.add(_42);
        _routesList.add(_43);
        _routesList.add(_43b);
        _routesList.add(_44);
        _routesList.add(_44b);
        _routesList.add(_45);
        _routesList.add(_46);
        _routesList.add(_47);
        _routesList.add(_48);
        _routesList.add(_49);
        _routesList.add(_50);
        _routesList.add(_51);
        _routesList.add(_52);
        _routesList.add(_52b);
        _routesList.add(_53);
        _routesList.add(_54);
        _routesList.add(_55);
        _routesList.add(_56);
        _routesList.add(_57);
        _routesList.add(_58);
        _routesList.add(_58b);
        _routesList.add(_59);
        _routesList.add(_59b);
        _routesList.add(_60);
        _routesList.add(_61);
        _routesList.add(_62);
        _routesList.add(_62b);
        _routesList.add(_63);
        _routesList.add(_63b);
        _routesList.add(_64);
        _routesList.add(_64b);
        _routesList.add(_65);
        _routesList.add(_65b);
        _routesList.add(_66);
        _routesList.add(_67);
        _routesList.add(_68);
        _routesList.add(_69);
        _routesList.add(_70);
        _routesList.add(_71);
        _routesList.add(_72);
        _routesList.add(_72b);
        _routesList.add(_73);
        _routesList.add(_74);
        _routesList.add(_75);
        _routesList.add(_76);
        _routesList.add(_77);
    }

    public boolean isRouteValid(IRoute route) {
        String start = route.getStart().get_name();
        String end = route.getEnd().get_name();

        for(IRoute r : _routesMap.values())
        {
            String s = r.getStart().get_name();
            String e = r.getEnd().get_name();
            if( (start.equals(s) && end.equals(e)) || (start.equals(e) && end.equals(s)) ){
                //Yep she's valid!
                return true;
            }
        }
        //If we get to here, then it is not a valid route
        return false;
    }

    public List<String> getRoutesList(){
        return this._routesList;
    }

    public Map<String, IRoute> getRoutesMap() {
        return _routesMap;
    }

    public IRoute isRouteDouble(IRoute route) {
        String sOne = route.getStart().get_name();
        String eOne = route.getEnd().get_name();

        int routeCount = 0;
        IRoute doubleRoute = null;
        for(IRoute r : _routesMap.values())
        {
            String sTwo = r.getStart().get_name();
            String eTwo = r.getEnd().get_name();
            if( (sOne.equals(sTwo) && eOne.equals(eTwo)) || (sOne.equals(eTwo) && eOne.equals(sTwo)) ){
                //Yep she's a match
                routeCount++;
                //Check if there is a difference in the coordinates and then we know there is a double
                int routeStartX = route.getStart().get_coordinates().x();
                int routeStartY = route.getStart().get_coordinates().y();
                int routeEndX = route.getEnd().get_coordinates().x();
                int routeEndY = route.getEnd().get_coordinates().y();
                int rStartX = r.getStart().get_coordinates().x();
                int rStartY = r.getStart().get_coordinates().y();
                int rEndX = r.getEnd().get_coordinates().x();
                int rEndY = r.getEnd().get_coordinates().y();

                if ((routeStartX != rStartX)  || (routeStartY != rStartY) || (routeEndX != rEndX)  || (routeEndY != rEndY)){
                    doubleRoute = r;
                    break;
                }


                if(routeCount == 2){
                    //We know we have a double.
                    doubleRoute = r;
                }
            }
        }
        //If we get to here and doubleRoute is not null, then we know we have the double route.
        if(doubleRoute == null){
            return null;
        }
        else{
            //Be sure to return a new route instance.
            return new Route(doubleRoute.getLength(),doubleRoute.getStart(),doubleRoute.getEnd(),doubleRoute.getColor(),false);
        }

    }
}
