package presenter;

import java.util.Observable;
import java.util.Observer;

import facade.guifacade.GameGuiFacade;
import model.ClientRoot;
import view.DrawTrainCardsActivity;
import view.ViewUtilities;

/**
 * Presenter for drawing train cards
 * Created by BenNelson on 3/6/18.
 */

public class DrawTrainCardsPresenter implements IDrawTrainCardsPresenter, Observer {

    private DrawTrainCardsActivity view;

    public DrawTrainCardsPresenter(DrawTrainCardsActivity view) {
        this.view = view;
        ClientRoot.addClientRootObserver(this);
    }

    @Override
    public String drawFaceUpCard(int index) {
        return GameGuiFacade.drawFaceUpTrainCard(index);
    }

    @Override
    public String drawFaceDownCard() {
        return GameGuiFacade.drawFaceDownTrainCard();
    }

    @Override
    public void update(Observable observable, Object o) {
        ViewUtilities.checkServerStatus(view);
        view.setFaceUpCards(ClientRoot.getClientGameInfo().getFaceUpCards());
        if(!new GameGuiFacade().checkTurn()){
            view.finish();
        }
    }
}
