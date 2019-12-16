package ml.lubster.services.printers;

import ml.lubster.tasks.TaskState;
import ml.lubster.services.converters.ITaskConverter;

/**
 * {@inheritDoc}
 * Prints to the system console.
 */
public class ConsoleTaskPrinter implements ITaskPrinter {
    /**
     * The Converter to convert {@link TaskState} to a String.
     */
    private ITaskConverter converter;

    /**
     * Creates an instance of the class.
     *
     * @param converter The Converter to convert {@link TaskState} to a String.
     */
    public ConsoleTaskPrinter(ITaskConverter converter) {
        this.converter = converter;
    }

    /**
     * {@inheritDoc} to the console.
     */
    @Override
    public void printTask(TaskState taskState) {
        System.out.println(converter.convert(taskState));
    }
}
