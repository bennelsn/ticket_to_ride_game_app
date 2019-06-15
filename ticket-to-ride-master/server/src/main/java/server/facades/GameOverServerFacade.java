package server.facades;

import java.util.ArrayList;
import java.util.List;

import server.facades.helpers.LongestPath;
import server.model.ServerRoot;
import server.poller.ClientCommands;

import shared.facades.server.IGameOverServerFacade;
import shared.model.City;
import shared.model.DestCard;
import shared.model.EndGameTotals;
import shared.model.Graph;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import shared.model.interfaces.IRoute;
import shared.results.GameOverResult;

/**
 * Created by paulinecausse on 3/24/18.
 */

public class GameOverServerFacade implements IGameOverServerFacade {
    public int getLongestRoute(IGame currentGame) {
        List<IPlayer> players = currentGame.getPlayers();
        List<IRoute> allRoutes = currentGame.getClaimedRoutes();

        IPlayer winner = null;
        int tempLP;
        int longestRoute = 0;
        for(IPlayer currentPlayer: players){
            Graph playerGraph = setPlayerGraph(currentPlayer, allRoutes);
            tempLP = getPlayerLongestRoute(playerGraph);
            if(tempLP > longestRoute){
                longestRoute = tempLP;
                winner = currentPlayer;
            }
        }

        if(winner == null){
            return -1;
        }
        currentGame.setPlayerWithLongestRoute(winner.getUsername());

        return getWinningPlayerID(winner,players);
    }

    private int getWinningPlayerID(IPlayer player,List<IPlayer> players){
        int i;
        for(i = 0; i < players.size(); i++){
            if(players.get(i).getUsername().equals(player.getUsername())){
                return i;
            }
        }

        return -1;
    }

    private int getPlayerLongestRoute(Graph graph){
        LongestPath lp = new LongestPath();
        int longestPath = 0;
        int tempLP;
        for(City city: graph.getVertices()){
            tempLP = lp.initLongestPath(graph.getVertices(),city);
            if(tempLP > longestPath){
                longestPath = tempLP;
            }
        }

        return longestPath;
    }

    private Graph setPlayerGraph(IPlayer player, List<IRoute> allRoutes){
        List<IRoute> playerRoute = setEdges(player,allRoutes);
        List<City> playerCities = setCities(playerRoute);

        return new Graph(playerRoute,playerCities,player.getUsername());
    }

    private List<IRoute> setEdges(IPlayer player, List<IRoute> allRoutes){
        List<IRoute> playerRoutes = new ArrayList<>();
        for(IRoute route:allRoutes){
            if(route.getOwner().equals(player.getUsername())){
                playerRoutes.add(route);
            }
        }

        return playerRoutes;
    }

    private List<City> setCities(List<IRoute> playerRoutes){
        List<City> playerCities = new ArrayList<>();
        for(IRoute route: playerRoutes){
            if(!findCity(playerCities,route.getStart())){
                playerCities.add(route.getStart());
            }
            if(!findCity(playerCities,route.getEnd())){
                playerCities.add(route.getEnd());
            }
        }

        return playerCities;
    }

    private boolean findCity(List<City> cities, City currentCity){
        boolean cityExists = false;
        for(City city:cities){
            if(currentCity.get_name().equals(city.get_name())){
                cityExists = true;
            }
        }

        return cityExists;
    }

    public GameOverResult getTotalPoints(IGame game){
        game = ServerRoot.getGame(game.getId());
        List<IPlayer> players = game.getPlayers();


        //Longest Route Bonus
        int winnerID = getLongestRoute(game);

        //A list of totals with indexes corresponding to the players
        List<EndGameTotals> endGameTotals = new ArrayList<>();

        //A list of claimed edges

        List<IRoute> routes = game.getClaimedRoutes();

        //Loop through each player to calculate points
        for (int i = 0; i < players.size(); i++){
            IPlayer current_player = ServerRoot.getPlayer(players.get(i).getUsername());
            EndGameTotals end = new EndGameTotals();

            //Claimed Destinations
            int claimed_destinations = calculateDestCards(current_player,routes);
            end.setDestination_card_points(claimed_destinations);

            //Unclaimed destinations
            int u_dest_points = 0;

            List<DestCard> unclaimed_cards = current_player.getDestCards();

            for (DestCard d : unclaimed_cards) {
                if(!d.isCompleted()){
                    u_dest_points -= d.getPoints();
                }
            }
            end.setUnclaimed_destination_points(u_dest_points);

            //Claimed Route points
            int claimed_route_points = current_player.getScore();
            end.setClaimed_route_points(claimed_route_points);

            if (winnerID == i){
                end.setLongest_route_bonus(10);
            }
            else
                end.setLongest_route_bonus(0);

            //Calculate total points
            int total_points = u_dest_points + claimed_route_points + end.getLongest_route_bonus() + claimed_destinations;
            end.setTotal_points(total_points);
            endGameTotals.add(end);
        }

        //Return the values
        return new GameOverResult(endGameTotals, true, ClientCommands.getCommandList(players.get(0).getUsername()));
    }


    private int calculateDestCards(IPlayer player, List<IRoute> routes){

        //Calculate claimed destination cards
        List<IRoute> current_routes = setEdges(player, routes);
        List<DestCard> current_cards = player.getDestCards();
        int total = 0;

        //Loop through DestCards
        for(int i = 0; i < current_cards.size(); i++){
            DestCard card = current_cards.get(i);
            String start_city = card.getStartingPoint();
            String end_city = card.getDestination();
            boolean isComplete = recursiveSearch(start_city,end_city,current_routes);
            card.setCompleted(isComplete);
            if(isComplete){
                total += card.getPoints();
            }
        }


        return total;
    }

    private boolean recursiveSearch(String start, String end, List<IRoute> routes){
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
        System.out.println("CITIES:");
        for(int i = 0; i < routes.size(); i++){
            String start_city = routes.get(i).getStart().get_name();
            String end_city = routes.get(i).getEnd().get_name();
            System.out.println(start_city + ",  " + end_city);
            if(start_city.equals(start)){
                List<IRoute> newRoute = new ArrayList<>(routes);
                newRoute.remove(i);
                if(recursiveSearch(end_city,end,newRoute))
                    return true;
            }
            else if(end_city.equals(start)){
                List<IRoute> newRoute = new ArrayList<>(routes);
                newRoute.remove(i);
                if( recursiveSearch(start_city,end,newRoute))
                    return true;
            }
            else if(( (start_city.equals(start) && end_city.equals(end) ) || (end_city.equals(start) && start_city.equals(end)) ))
            {
                System.out.println("TRue");
                return true;
            }
        }
        if(start.equals(end))
            return true;
        return false;
    }

}
