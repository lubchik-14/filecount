package ml.lubster.services.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * {@inheritDoc}
 * Writes into a file.
 */
public class FileLineWriter implements IWriter<String> {
    /**
     * PrintWriter to write data.
     */
    private PrintWriter printWriter;

    /**
     * Creates an instance of the class. Uses {@link PrintWriter} to write data.
     * @param fileName destined file.
     */
    public FileLineWriter(String fileName) {
        try {
            this.printWriter = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.err.println("Exception was occurred while creating an instance of PrintWriter");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(String string) {
        printWriter.println(string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        printWriter.close();
    }
}
