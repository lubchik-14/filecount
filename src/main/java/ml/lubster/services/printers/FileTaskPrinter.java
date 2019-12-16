package ml.lubster.services.printers;

import ml.lubster.tasks.TaskState;
import ml.lubster.services.converters.ITaskConverter;
import ml.lubster.services.writers.IWriter;

/**
 * {@inheritDoc}
 * Prints to a file.
 */
public class FileTaskPrinter implements ITaskPrinter {
    /**
     * The Converter to convert {@link TaskState} to a String.
     */
    private final ITaskConverter converter;
    /**
     * The Writer to write information.
     */
    private final IWriter<String> writer;

    /**
     * Creates an instance of the class.
     *
     * @param converter The Converter to convert {@link TaskState} to a String.
     * @param writer The Writer to write information.
     */
    public FileTaskPrinter(ITaskConverter converter, IWriter<String> writer) {
        this.converter = converter;
        this.writer = writer;
    }

    /**
     * {@inheritDoc} to the file.
     */
    @Override
    public void printTask(TaskState taskState) {
        if (taskState.getTaskState() != TaskState.State.CANCELLED
                && taskState.getTaskState() != TaskState.State.INVALID) {
            writer.write(converter.convert(taskState));
        }
    }
}
