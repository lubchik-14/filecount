package ml.lubster.services.printers;

import ml.lubster.tasks.TaskState;

/**
 * Prints information about a task.
 */
public interface ITaskPrinter {
    /**
     * Prints {@link TaskState}.
     *
     * @param taskState the given state of the task.
     */
    void printTask(TaskState taskState);
}
