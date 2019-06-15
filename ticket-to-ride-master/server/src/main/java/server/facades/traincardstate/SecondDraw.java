package server.facades.traincardstate;

import server.facades.GameServerFacade;
import shared.logging.Logger;
import shared.model.Color;
import shared.model.TrainCard;
import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 3/24/18.
 */

public class SecondDraw extends TrainCardState {
    public SecondDraw(GameServerFacade gameServerFacade) {
        super(gameServerFacade);
        Logger.log("We are entering the second phase of train card draw.");
    }

    @Override
    public TrainCard drawFaceUpCard(IGame game, int cardIndex) {
        System.out.println("Draw a face up card in state two.");
        //Get card
        TrainCard result = game.getCardsFaceUp().get(cardIndex);
        //if locomotive
        if(result.getColor().equals(Color.RAINBOW)){
            //Do nothing
            gameServerFacade.setState(new SecondDraw(gameServerFacade));
            return null;
        } else {
            if(game.getCardsFaceUp().size() < 2){
                gameServerFacade.setState(new NoCards(gameServerFacade));
            }
            gameServerFacade.setState(new FirstDraw(gameServerFacade));
            game.incrementTurnIndex();
            return result;
        }
    }

    @Override
    public TrainCard drawFaceDownCard(IGame game) {
        gameServerFacade.setState(new FirstDraw(gameServerFacade));
        game.incrementTurnIndex();
        return this.gameServerFacade.drawCardFromDeck(game.getId());
    }
}
