package ml.lubster.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class-validator for paths.
 */
public class Validator {
    /**
     * Checks the given path out.
     *
     * @param path String path to validate.
     * @return true if the given path not empty and exists.
     */
    public static boolean isPathValid(String path) {
        if (!path.equals("") && new File(path).isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * Checks the given path to file out.
     *
     * @param path String path to file to validate.
     * @return true if the given path not empty and the file exists.
     */
    public static boolean isFileExist(String path) {
        return !path.equals("") && Files.exists(Paths.get(path)) && new File(path).isFile();
    }

    public static boolean isAccessToFile(String path) {

        return !path.equals("") && Files.exists(Paths.get(path)) && new File(path).isFile();
    }
}
