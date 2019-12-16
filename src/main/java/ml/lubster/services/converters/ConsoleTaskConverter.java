package ml.lubster.services.converters;

import ml.lubster.tasks.TaskState;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@inheritDoc}
 */
public class ConsoleTaskConverter implements ITaskConverter {
    private final String TABLE_PATTERN = "%-3s | %-10s | %s %s";
    private AtomicInteger orderNumber = new AtomicInteger(0);

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(TaskState taskState) {
        String path = taskState.getPath();
        String count = taskState.getFileCount().toString();
        String status = "";
        switch (taskState.getTaskState()) {
            case CANCELLED: {
                status = "(cancelled)";
                break;
            }
            case INVALID: {
                status = "(invalid path)";
                break;
            }
            case NO_ACCESS: {
                status = "(no access to some files)";
                break;
            }
        }
        return String.format(TABLE_PATTERN, orderNumber.incrementAndGet(), count, path, status);
    }
}
