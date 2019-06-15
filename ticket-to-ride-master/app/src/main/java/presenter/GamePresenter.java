package presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import facade.guifacade.GameGuiFacade;
import model.ClientRoot;
import proxies.ServerProxy;
import shared.model.City;
import shared.model.Color;
import shared.model.Hand;
import shared.model.Route;
import shared.model.Train;
import shared.model.TrainCard;
import shared.model.TrainCardSet;
import shared.model.initialized_info.Cities;
import shared.model.interfaces.IGameInfo;
import shared.model.interfaces.IPlayer;
import shared.model.interfaces.IRoute;
import view.GameActivity;
import view.GameInfoActivity;
import view.ViewUtilities;

/**
 * This class is the presenter for the game
 * Created by BenNelson on 3/6/18.
 */

public class GamePresenter implements IGamePresenter, Observer{
    private GameActivity _gameActivity;
    private boolean updatable;

    //This presenter needs to add itself as an observer to the client
    public GamePresenter(GameActivity gameActivity){
        this._gameActivity = gameActivity;
        ClientRoot.addClientRootObserver(this);
        ServerProxy sproxy  = new ServerProxy();
        IGameInfo gi = sproxy.getGameInfo(ClientRoot.getClientPlayer().getUsername()).getGameInfo();
        ClientRoot.setClientGameInfo(gi);
        IPlayer player = sproxy.getPlayerInfo(ClientRoot.getClientPlayer().getUsername()).getPlayerInfo();
        ClientRoot.setClientPlayer(player);
        int yes = 0;
        updatable = true;
    }

    @Override
    public void update(Observable o, Object arg) {

        ViewUtilities.checkServerStatus(_gameActivity);

        if(!updatable){
            return;
        }
        _gameActivity.checkTurn();
        if(ClientRoot.getClientGameInfo() != null){
            _gameActivity.drawRoutes();
        }

    }

    @Override
    public String claimRoute(List<String> startEndCities, TrainCardSet trainCards) {
        String startCity = startEndCities.get(0);
        String endCity = startEndCities.get(1);
        City start = Cities.instance().getCity(startCity);
        City end = Cities.instance().getCity(endCity);
        IRoute route = new Route(start,end);

        return GameGuiFacade.claimRoute(route,trainCards, ClientRoot.getClientPlayer().getUsername());
    }

    @Override
    public Map<Color, List<IRoute>> getRoutesMapForDrawing() {
        Map<Color, List<IRoute>> drawMap = new HashMap<>();
        Map<String, List<IRoute>> routesMap = ClientRoot.getClientGameInfo().getClaimedRoutes();

        for(String player: routesMap.keySet()){
            List<IRoute> routes = routesMap.get(player);
            Integer playerColor = ClientRoot.getClientGameInfo().getPlayerColors().get(player);
            switch(playerColor){
                case Color.PLAYER_RED:
                    drawMap.put(Color.RED, routes);
                    break;
                case Color.PLAYER_GREEN:
                    drawMap.put(Color.GREEN, routes);
                    break;
                case Color.PLAYER_YELLOW:
                    drawMap.put(Color.YELLOW, routes);
                    break;
                case Color.PLAYER_BLUE:
                    drawMap.put(Color.BLUE, routes);
                    break;
                case Color.PLAYER_BLACK:
                    drawMap.put(Color.BLACK, routes);
                    break;
                default:
                    break;
            }
        }

        return drawMap;
    }

    @Override
    public boolean checkTurn(){
        System.out.println(GameGuiFacade.checkTurn());
        return GameGuiFacade.checkTurn();
    }

    @Override
    public boolean userHasCards(Color color, int cardCount) {
        List<TrainCard> trainCards = ClientRoot.getClientPlayer().getTrainCardHand().get_cards();
        for(TrainCard card : trainCards){
            if(color.equals(card.getColor())){
                cardCount--;
            }
        }
        if(cardCount <= 0){
            return true;
        }
        else {
            return false;
        }
    }


}
