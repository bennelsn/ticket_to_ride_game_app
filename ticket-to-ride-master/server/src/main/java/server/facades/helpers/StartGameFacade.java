package server.facades.helpers;

import java.util.ArrayList;
import java.util.List;

import server.model.ServerRoot;
import shared.model.DestCard;
import shared.model.DestDeck;
import shared.model.interfaces.IGame;
import shared.model.interfaces.IPlayer;
import shared.model.Train;
import shared.model.TrainCard;
import shared.model.TrainDeck;

/**
 * Created by bdemann on 3/3/18.
 * I am not sure how we are going to interact with this class. Maybe we just will want to take methods out of this class and move them else where but it will be me a starting place
 */

public class StartGameFacade {


    // give players their trains
    // set each score to 0
    // shuffle decks
    // deal cards to each player
    // put out top five cards
    // initialize the longest path.
    // deal out 3 destination cards
    // let each person know that they need to pick at least two cards
    public static IGame setUpGame(IGame game){
        game = initTrains(game);
        game = initScore(game);
        game = shuffleDecks(game);
        game = dealStartingTrainCards(game, 4);
        game = initDrawPile(game);
        game = initLongestPath(game);
        game = dealStartingDestCards(game, 3);
        return game;
    }

    private static IGame dealStartingDestCards(IGame game, int startingDestCardCount) {
        DestDeck destDeck = game.getDestCardDeck();
        for(IPlayer player : game.getPlayers()) {
            List<DestCard> startingTickets = destDeck.draw(startingDestCardCount);
            ServerRoot.getPlayer(player.getUsername()).setUnresolvedDestCards(startingTickets);
        }
        return game;
    }

    private static IGame initLongestPath(IGame game) {
        game.setPlayerWithLongestRoute("");
        return game;
    }

    private static IGame initDrawPile(IGame game) {
        game.setCardsFaceUp(game.getTrainCardDeck().draw(5));
        return game;
    }

    private static IGame dealStartingTrainCards(IGame game, int startingHandSize) {
        //For each player
        //  draw 4 cards from the deck
        //  put those cards into the player's hand
        TrainDeck trainDeck = game.getTrainCardDeck();
        for(IPlayer player : game.getPlayers()){
            List<TrainCard> startingHand = trainDeck.draw(startingHandSize);
            ServerRoot.getPlayer(player.getUsername()).setTrainCards(startingHand);
        }
        return game;
    }

    private static IGame shuffleDecks(IGame game) {
        game.getTrainCardDeck().shuffle();
        game.getDestCardDeck().shuffle();
        return game;
    }

    private static IGame initScore(IGame game) {
        for (IPlayer player : game.getPlayers()){
            ServerRoot.getPlayer(player.getUsername()).setScore(0);
        }
        return game;
    }

    private static IGame initTrains(IGame game) {
        for(IPlayer player : game.getPlayers()) {
            ServerRoot.getPlayer(player.getUsername()).setTrains(initTrainList());
        }
        return game;
    }

    private static List<Train> initTrainList() {
        List<Train> trains = new ArrayList<>();
        for (int i = 0; i < 45; i++){
            trains.add(new Train());
        }
        return trains;
    }

}
