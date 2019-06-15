package meta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by bdemann on 4/11/18.
 */

public class MetaFile {

    String className;

    public MetaFile(String className) {
        this.className = className;
    }

    public static MetaFile makeMeta(String pathToPlugin) {
        String pathToMeta = pathToPlugin.replace(".jar", ".meta");
        File file = new File(pathToMeta);
        BufferedReader br = null;
        String className = "";
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            className = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MetaFile(className);
    }

    public String getClassName() {
        return className;
    }
}
