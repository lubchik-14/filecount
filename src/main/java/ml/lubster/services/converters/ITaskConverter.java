package ml.lubster.services.converters;

import ml.lubster.tasks.TaskState;

/**
 * Converts TaskState into a special format.
 */
public interface ITaskConverter {
    /**
     * Returns String from task state.
     *
     * @param taskState state of finished task.
     * @return String from the given task state.
     */
    String convert(TaskState taskState);
}
