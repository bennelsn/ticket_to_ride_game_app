package presenter;


import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import facade.guifacade.GameGuiFacade;
import model.ClientRoot;
import shared.model.DestCard;
import shared.model.DestCardSet;
import view.DrawDestinationsActivity;
import view.ViewUtilities;

/**
 *
 * Created by BenNelson on 3/6/18.
 */

public class DrawDestinationsPresenter implements IDrawDestinationsPresenter, Observer {


    private DrawDestinationsActivity activity;
    public DrawDestinationsPresenter(DrawDestinationsActivity activity){
        this.activity = activity;
        ClientRoot.addClientRootObserver(this);

    }
    @Override
    public List<DestCard> drawDestinationCards() {
        return GameGuiFacade.drawDestinationCards();
    }

    @Override
    public List<DestCard> getStarterDestinationCards() {
        return GameGuiFacade.getStarterDestinationCards();
    }

    @Override
    public void discardDestCards(List<DestCard> chosenDestList, DestCardSet removeDestCardSet){
        GameGuiFacade.discardDestinationCards(chosenDestList,removeDestCardSet);
    }

    @Override
    public void update(Observable o, Object arg) {
        ViewUtilities.checkServerStatus(activity);
    }
}
