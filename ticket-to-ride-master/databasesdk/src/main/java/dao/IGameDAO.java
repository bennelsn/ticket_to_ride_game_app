package dao;

import java.util.List;

import shared.model.interfaces.IGame;

/**
 * Created by bdemann on 4/9/18.
 */

public interface IGameDAO {
    public boolean addGame(IGame game);
    public List<IGame> getGames();
    public void deleteGames();

}
