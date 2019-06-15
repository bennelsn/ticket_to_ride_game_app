package presenter;

/**
 * Interface for Drawing train cards
 * Created by BenNelson on 3/6/18.
 */

public interface IDrawTrainCardsPresenter {
    String drawFaceUpCard(int index);
    String drawFaceDownCard();
}
