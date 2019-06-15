package server.pluginmanager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import dao.IModelDAO;
import meta.MetaFile;

/**
 * Created by bdemann on 4/10/18.
 */

public class PluginManager {

    public static void main(String[] args) {
        IModelDAO loadMe = createModelDAO("/home/bdemann/Documents/cs340/ticket-to-ride/server/plugins/Test.jar");
    }

    public static IModelDAO createModelDAO(String pathToPlugin) {
        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[1];
        try {
            classLoaderUrls[0] = new URL("file://" + pathToPlugin);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("URL 1: " + classLoaderUrls[0]);

        // Create a new URLClassLoader
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        // Load the target class
        Class<?> daoClass = null;
        try {
            System.out.println("DaoClass: " + getClassName(pathToPlugin));
            daoClass = urlClassLoader.loadClass(getClassName(pathToPlugin));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Create a new instance from the loaded class
        Constructor<?> constructor = null;
        try {
            constructor = daoClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object daoObj = null;
        try {
            daoObj = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (IModelDAO) daoObj;
    }

    private static String getClassName(String pathToPlugin) {
        MetaFile metaFile = MetaFile.makeMeta(pathToPlugin);
        return metaFile.getClassName();
    }
}
