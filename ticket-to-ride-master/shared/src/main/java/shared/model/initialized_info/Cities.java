package shared.model.initialized_info;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import shared.model.City;
import shared.model.CityPoint;

import static shared.model.initialized_info.DestCardId.*;

/**
 *
 * Created by paulinecausse on 3/3/18.
 */

public class Cities implements Serializable {
    private static Cities _instance;
    private Map<String, City> citiesMap;

    private City vanc = new City(new CityPoint(132,18), VANCOUVER);
    private City vancOFFSET = new City(new CityPoint(122,18), VANCOUVER);
    private City winn = new City(new CityPoint(524, 62), WINNIPEG);
    private City cal = new City(new CityPoint(275, 18), CALGARY);
    private City mont = new City(new CityPoint(960, 83), MONTREAL);
    private City montOFFSET = new City(new CityPoint(950, 86), MONTREAL);
    private City seat = new City(new CityPoint(131, 68), SEATTLE);
    private City seatOFFSET = new City(new CityPoint(121, 68), SEATTLE);
    private City port = new City(new CityPoint(96, 113), PORTLAND);
    private City portOFFSET = new City(new CityPoint(86, 113), PORTLAND);
    private City hel = new City(new CityPoint(338, 161), HELENA);
    private City dul = new City(new CityPoint(604, 197), DULUTH);
    private City dulOFFSET = new City(new CityPoint(594, 197), DULUTH);
    private City stm = new City(new CityPoint(737, 144), SAUL_ST_MARIE);
    private City tor = new City(new CityPoint(841, 171), TORONTO);
    private City bost = new City(new CityPoint(980, 171), BOSTON);
    private City bostOFFSET = new City(new CityPoint(970, 171), BOSTON);
    private City nyc = new City(new CityPoint(940, 240), NEW_YORK);
    private City nycOFFSET = new City(new CityPoint(935, 230), NEW_YORK);
    private City pitt = new City(new CityPoint(839, 269), PITTSBURGH);
    private City wash = new City(new CityPoint(892, 329), WASHINGTON);
    private City washOFFSET = new City(new CityPoint(892, 319), WASHINGTON);
    private City chic = new City(new CityPoint(715, 269), CHICAGO);
    private City omhC = new City(new CityPoint(521, 395), OKLAHOMA_CITY);
    private City omhCOFFSET = new City(new CityPoint(511, 395), OKLAHOMA_CITY);
    private City omh = new City(new CityPoint(554, 264), OMAHA);
    private City omhOFFSET = new City(new CityPoint(544, 264), OMAHA);
    private City dvr = new City(new CityPoint(384, 311), DENVER);
    private City dvrOFFSET = new City(new CityPoint(384, 321), DENVER);
    private City kanC = new City(new CityPoint(560, 327), KANSAS_CITY);
    private City kanCOFFSET = new City(new CityPoint(560, 317), KANSAS_CITY);
    private City stL = new City(new CityPoint(644, 336), ST_LOUIS);
    private City nash = new City(new CityPoint(740, 387), NASHVILLE);
    private City ral = new City(new CityPoint(841, 381), RALEIGH);
    private City ralOFFSET = new City(new CityPoint(841, 371), RALEIGH);
    private City atl = new City(new CityPoint(781, 436), ATLANTA);
    private City atlOFFSET = new City(new CityPoint(781, 426), ATLANTA);
    private City charl = new City(new CityPoint(862, 450), CHARLESTON);
    private City miami = new City(new CityPoint(880, 617), MIAMI);
    private City newO = new City(new CityPoint(672, 548), NEW_ORLEANS);
    private City newOOFFSET = new City(new CityPoint(672, 538), NEW_ORLEANS);
    private City lRock = new City(new CityPoint(614, 417), LITTLE_ROCK);
    private City hou = new City(new CityPoint(585, 540), HOUSTON);
    private City houOFFSET = new City(new CityPoint(575, 540), HOUSTON);
    private City dal = new City(new CityPoint(560, 503), DALLAS);
    private City dalOFFSET = new City(new CityPoint(550, 503), DALLAS);
    private City elP = new City(new CityPoint(345, 480), EL_PASO);
    private City sanFe = new City(new CityPoint(357, 399), SANTA_FE);
    private City phx = new City(new CityPoint(236, 429), PHOENIX);
    private City lasV = new City(new CityPoint(197, 369), LAS_VEGAS);
    private City laxOFFSET = new City(new CityPoint(131, 416), LOS_ANGELES);
    private City lax = new City(new CityPoint(121, 416), LOS_ANGELES);
    private City sanFr = new City(new CityPoint(65, 306), SAN_FRANCISCO);
    private City sanFrOFFSET = new City(new CityPoint(65, 316), SAN_FRANCISCO);
    private City slc = new City(new CityPoint(261, 264), SALT_LAKE_CITY);
    private City slcOFFSET = new City(new CityPoint(261, 274), SALT_LAKE_CITY);

    public static Cities instance() {

        if (_instance == null)
            _instance = new Cities();
        return _instance;
    }

    private Cities(){
        citiesMap = new HashMap<>();
        citiesMap.put(VANCOUVER, vanc);
        citiesMap.put(WINNIPEG, winn);
        citiesMap.put(CALGARY, cal);
        citiesMap.put(MONTREAL, mont);
        citiesMap.put(SEATTLE, seat);
        citiesMap.put(PORTLAND, port);
        citiesMap.put(HELENA, hel);
        citiesMap.put(DULUTH, dul);
        citiesMap.put(SAUL_ST_MARIE, stm);
        citiesMap.put(TORONTO, tor);
        citiesMap.put(BOSTON, bost);
        citiesMap.put(NEW_YORK, nyc);
        citiesMap.put(PITTSBURGH, pitt);
        citiesMap.put(WASHINGTON, wash);
        citiesMap.put(CHICAGO, chic);
        citiesMap.put(OKLAHOMA_CITY, omhC);
        citiesMap.put(OMAHA, omh);
        citiesMap.put(DENVER, dvr);
        citiesMap.put(KANSAS_CITY, kanC);
        citiesMap.put(ST_LOUIS, stL);
        citiesMap.put(NASHVILLE, nash);
        citiesMap.put(RALEIGH, ral);
        citiesMap.put(ATLANTA, atl);
        citiesMap.put(CHARLESTON, charl);
        citiesMap.put(MIAMI, miami);
        citiesMap.put(NEW_ORLEANS, newO);
        citiesMap.put(LITTLE_ROCK, lRock);
        citiesMap.put(HOUSTON, hou);
        citiesMap.put(DALLAS, dal);
        citiesMap.put(EL_PASO, elP);
        citiesMap.put(SANTA_FE, sanFe);
        citiesMap.put(PHOENIX, phx);
        citiesMap.put(LAS_VEGAS, lasV);
        citiesMap.put(LOS_ANGELES, lax);
        citiesMap.put(SAN_FRANCISCO, sanFr);
        citiesMap.put(SALT_LAKE_CITY, slc);
    }



    public City getVanc() {
        return vanc;
    }

    public City getVancOFFSET() {
        return vancOFFSET;
    }

    public City getWinn() {
        return winn;
    }

    public City getCal() {
        return cal;
    }

    public City getMont() {
        return mont;
    }

    public City getMontOFFSET() {
        return montOFFSET;
    }

    public City getSeat() {
        return seat;
    }

    public City getSeatOFFSET() {
        return seatOFFSET;
    }

    public City getPort() { return port; }

    public City getPortOFFSET() { return portOFFSET; }

    public City getHel() {
        return hel;
    }

    public City getDul() {
        return dul;
    }

    public City getDulOFFSET() {
        return dulOFFSET;
    }

    public City getStm() {
        return stm;
    }

    public City getTor() {
        return tor;
    }

    public City getBost() {
        return bost;
    }

    public City getBostOFFSET() {
        return bostOFFSET;
    }

    public City getNyc() {
        return nyc;
    }

    public City getNycOFFSET() {
        return nycOFFSET;
    }

    public City getPitt() {
        return pitt;
    }

    public City getWash() {
        return wash;
    }

    public City getWashOFFSET() {
        return washOFFSET;
    }

    public City getChic() {
        return chic;
    }

    public City getOmhC() {
        return omhC;
    }

    public City getOmhCOFFSET() {
        return omhCOFFSET;
    }

    public City getOmh() {
        return omh;
    }

    public City getOmhOFFSET() {
        return omhOFFSET;
    }

    public City getDvr() {
        return dvr;
    }

    public City getDvrOFFSET() {
        return dvrOFFSET;
    }

    public City getKanC() {
        return kanC;
    }

    public City getKanCOFFSET() {
        return kanCOFFSET;
    }

    public City getStL() {
        return stL;
    }

    public City getNash() {
        return nash;
    }

    public City getRal() {
        return ral;
    }

    public City getRalOFFSET() {
        return ralOFFSET;
    }

    public City getAtl() {
        return atl;
    }

    public City getAtlOFFSET() {
        return atlOFFSET;
    }

    public City getCharl() {
        return charl;
    }

    public City getMiami() {
        return miami;
    }

    public City getNewO() {
        return newO;
    }

    public City getNewOOFFSET() {
        return newOOFFSET;
    }

    public City getlRock() {
        return lRock;
    }

    public City getHou() {
        return hou;
    }

    public City getHouOFFSET() {
        return houOFFSET;
    }

    public City getDal() {
        return dal;
    }

    public City getDalOFFSET() {
        return dalOFFSET;
    }

    public City getElP() {
        return elP;
    }

    public City getSanFe() {
        return sanFe;
    }

    public City getPhx() {
        return phx;
    }

    public City getLasV() {
        return lasV;
    }

    public City getLax() {
        return lax;
    }

    public City getLaxOFFSET() {
        return laxOFFSET;
    }

    public City getSanFr() {
        return sanFr;
    }

    public City getSanFrOFFSET() {
        return sanFrOFFSET;
    }

    public City getSlc() {
        return slc;
    }

    public City getSlcOFFSET() {return slcOFFSET;}

    public City getCity(String cityName) {

        if(citiesMap.containsKey(cityName)){
            return citiesMap.get(cityName);
        }
        else {
            return null;
        }

    }
}
