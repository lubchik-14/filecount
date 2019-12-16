package ml.lubster.services.printers;

import ml.lubster.tasks.TaskState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Delegates getting information to {@link DelegateTaskPrinter#printers}.
 */
public class DelegateTaskPrinter implements ITaskPrinter {
    /**
     * List of {@link ITaskPrinter} to print the given {@link TaskState}.
     */
    public final List<ITaskPrinter> printers;

    /**
     * Creates an instance of the class.
     *
     * @param printers
     */
    public DelegateTaskPrinter(ITaskPrinter... printers) {
        this.printers = new ArrayList<>(Arrays.asList(printers));
    }

    /**
     * Delegates printing of header of the table to {@link DelegateTaskPrinter#printers}.
     */
    @Override
    public void printTask(TaskState taskState) {
        printers.forEach(printer -> printer.printTask(taskState));
    }
}
