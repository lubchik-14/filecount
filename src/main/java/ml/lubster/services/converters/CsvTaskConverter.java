package ml.lubster.services.converters;

import ml.lubster.tasks.TaskState;

/**
 * Converts TaskState into CSV format.
 */
public class CsvTaskConverter implements ITaskConverter {
    private final String TABLE_PATTERN = "%s%s%s";
    private final String delimiter;

    /**
     * Creates an instance of the class.
     * @param delimiter the delimiter to separate values.
     */
    public CsvTaskConverter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(TaskState taskState) {
        String path = taskState.getPath();
        if (path.contains(",")) {
            path = "\"" + path + "\"";
        }
        String count = taskState.getFileCount().toString();
        return String.format(TABLE_PATTERN, path, delimiter, count);
    }
}
