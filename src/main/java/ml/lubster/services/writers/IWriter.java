package ml.lubster.services.writers;

/**
 * Describe a writer to write data.
 * @param <T> Type of data.
 */
public interface IWriter<T> {
    /**
     * Writes the given data.
     *
     * @param t data to write.
     */
    void write(T t);

    /**
     * Closes any opened connection.
     */
    void close();
}