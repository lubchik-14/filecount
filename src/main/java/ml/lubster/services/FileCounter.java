package ml.lubster.services;

import ml.lubster.tasks.TaskState;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * {@inheritDoc}
 */
public class FileCounter extends SimpleFileVisitor<Path> {
    private TaskState taskState;

    /**
     * Creates an instance of the class.
     * @param taskState the state of the task {@link TaskState}.
     */
    public FileCounter(TaskState taskState) {
        this.taskState = taskState;
    }

    /**
     * {@inheritDoc}
     * Increments the file count of the given {@link TaskState}.
     * Finished visiting if the current thread is interrupted.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        taskState.incrementFileCount();
        if (Thread.currentThread().isInterrupted()) {
            taskState.setState(TaskState.State.CANCELLED);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * {@inheritDoc}
     * Finished visiting if the current thread is interrupted.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) {
        if (e instanceof AccessDeniedException) {
            taskState.setState(TaskState.State.NO_ACCESS);
        }
        if (Thread.currentThread().isInterrupted()) {
            taskState.setState(TaskState.State.CANCELLED);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.SKIP_SUBTREE;
    }
}
