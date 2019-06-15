package shared.model.initialized_info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import shared.model.DestCard;

import static shared.model.initialized_info.DestCardId.*;

/**
 * Created by paulinecausse on 2/27/18.
 */

public class DestinationCards implements Serializable {
    private static DestinationCards _instance = new DestinationCards();

    public static DestinationCards instance() {
        return _instance;
    }

    private DestinationCards(){
        this._destCards = new ArrayList<DestCard>();
        set_destCards();
    }

    public static List<DestCard> getDestinationCards() {
        return instance()._destCards;
    }

    private List<DestCard> _destCards;

    private void set_destCards(){
        DestCard _1 = new DestCard(NEW_YORK, LOS_ANGELES, 21);
        DestCard _2 = new DestCard(HOUSTON, DULUTH, 8);
        DestCard _3 = new DestCard(NASHVILLE, SAUL_ST_MARIE, 8);
        DestCard _4 = new DestCard(ATLANTA, NEW_YORK, 6);
        DestCard _5 = new DestCard(NASHVILLE, PORTLAND, 17);
        DestCard _6 = new DestCard(MONTREAL, VANCOUVER, 20);
        DestCard _7 = new DestCard(EL_PASO, DULUTH, 10);
        DestCard _8 = new DestCard(MIAMI, TORONTO ,10);
        DestCard _9 = new DestCard(PHOENIX, PORTLAND, 11);
        DestCard _10 = new DestCard(NEW_YORK, DALLAS, 11);
        DestCard _11 = new DestCard(SALT_LAKE_CITY, CALGARY, 7);
        DestCard _12 = new DestCard(PHOENIX, CALGARY, 13);
        DestCard _13 = new DestCard(MIAMI, LOS_ANGELES, 20);
        DestCard _14 = new DestCard(LITTLE_ROCK, WINNIPEG, 11);
        DestCard _15 = new DestCard(ATLANTA, SAN_FRANCISCO, 17);
        DestCard _16 = new DestCard(HOUSTON, KANSAS_CITY,  5);
        DestCard _17 = new DestCard(CHICAGO, LOS_ANGELES, 16);
        DestCard _18 = new DestCard(PITTSBURGH, DENVER, 11);
        DestCard _19 = new DestCard(SANTA_FE, CHICAGO, 9);
        DestCard _20 = new DestCard(SANTA_FE, VANCOUVER, 13);
        DestCard _21 = new DestCard(MIAMI, BOSTON, 12);
        DestCard _22 = new DestCard(NEW_ORLEANS, CHICAGO, 7);
        DestCard _23 = new DestCard(ATLANTA, MONTREAL, 9);
        DestCard _24 = new DestCard(NEW_YORK, SEATTLE, 22);
        DestCard _25 = new DestCard(EL_PASO, DENVER, 4);
        DestCard _26 = new DestCard(LOS_ANGELES, HELENA, 8);
        DestCard _27 = new DestCard(HOUSTON, WINNIPEG, 12);
        DestCard _28 = new DestCard(NEW_ORLEANS, MONTREAL, 13);
        DestCard _29 = new DestCard(OKLAHOMA_CITY, SAUL_ST_MARIE, 9);
        DestCard _30 = new DestCard(LOS_ANGELES, SEATTLE, 9);

        _destCards.add(_1);
        _destCards.add(_2);
        _destCards.add(_3);
        _destCards.add(_4);
        _destCards.add(_5);
        _destCards.add(_6);
        _destCards.add(_7);
        _destCards.add(_8);
        _destCards.add(_9);
        _destCards.add(_10);
        _destCards.add(_11);
        _destCards.add(_12);
        _destCards.add(_13);
        _destCards.add(_14);
        _destCards.add(_15);
        _destCards.add(_16);
        _destCards.add(_17);
        _destCards.add(_18);
        _destCards.add(_19);
        _destCards.add(_20);
        _destCards.add(_21);
        _destCards.add(_22);
        _destCards.add(_23);
        _destCards.add(_24);
        _destCards.add(_25);
        _destCards.add(_26);
        _destCards.add(_27);
        _destCards.add(_28);
        _destCards.add(_29);
        _destCards.add(_30);
    }

}