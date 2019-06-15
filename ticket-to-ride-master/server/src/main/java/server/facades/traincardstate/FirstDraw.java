package server.facades.traincardstate;

import server.facades.GameServerFacade;
import shared.logging.Logger;
import shared.model.Color;
import shared.model.TrainCard;
import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 3/24/18.
 */

public class FirstDraw extends TrainCardState {

    public FirstDraw(GameServerFacade gameServerFacade) {
        super(gameServerFacade);
        Logger.log("We are entering the first phase of train card draw.");
    }

    @Override
    public TrainCard drawFaceUpCard(IGame game, int cardIndex) {
        System.out.println("Draw a face up card in state one.");
        //Get card
        TrainCard result = game.getCardsFaceUp().get(cardIndex);
        //if locomotive
        if(result.getColor().equals(Color.RAINBOW)){
            game.incrementTurnIndex();
            gameServerFacade.setState(new FirstDraw(gameServerFacade));
        } else {
            gameServerFacade.setState(new SecondDraw(gameServerFacade));
        }
        return result;
    }

    @Override
    public TrainCard drawFaceDownCard(IGame game) {
        gameServerFacade.setState(new SecondDraw(gameServerFacade));
        return this.gameServerFacade.drawCardFromDeck(game.getId());
    }

}
