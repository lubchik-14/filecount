package ml.lubster.services.readers;

import java.util.List;

/**
 * Interface to read data.
 *
 * @param <T> Type of data.
 */
public interface IReader<T> {
    List<T> read();
}