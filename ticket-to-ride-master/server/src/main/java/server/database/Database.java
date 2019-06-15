package server.database;

import dao.IModelDAO;
import server.model.ServerRoot;
import server.pluginmanager.PluginManager;

/**
 * Created by bdemann on 4/11/18.
 */

public class Database {

    private IModelDAO modelDAO;
    private static Database _instance = new Database();

    public static IModelDAO getModelDAO() {
        if (_instance.modelDAO == null) {
            _instance.modelDAO = PluginManager.createModelDAO(ServerRoot.getPluginPath());
        }
        return _instance.modelDAO;
    }
}
