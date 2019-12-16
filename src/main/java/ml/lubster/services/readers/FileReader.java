package ml.lubster.services.readers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * {@inheritDoc}
 * Reader files line by line.
 */
public class FileReader implements IReader<String> {
    /**
     * The path to the file.
     */
    private String path;

    /**
     * Creates an instance of the class.
     *
     * @param path The path to the file.
     */
    public FileReader(String path) {
        this.path = path;
    }

    /**
     * Reads the given input file and returns List of its lines.
     *
     * @return List of lines from the given file.
     */
    @Override
    public List<String> read() {
        List<String> lines = new ArrayList<>();
        try (Scanner in = new Scanner(new FileInputStream(path))) {
            while (in.hasNext()) {
                String line = in.nextLine();
                if (!line.equals("")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.printf("Exception was occurred reading '%s'\n", path);
            System.exit(0);
        }
        return lines;
    }
}
