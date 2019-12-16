package ml.lubster.tasks;

import ml.lubster.services.Decoder;
import ml.lubster.services.FileCounter;
import ml.lubster.services.Validator;
import ml.lubster.services.printers.ITaskPrinter;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The task to search all files in the file tree.
 */
public class FileCountTask implements Runnable {
    /**
     * Printer to print the state of the task.
     */
    private final ITaskPrinter printer;
    /**
     * The state of the task
     */
    private TaskState state;

    /**
     * Creates an instance of the class
     *
     * @param printer to print the state of the task.
     * @param path String uri to start search from.
     */
    public FileCountTask(ITaskPrinter printer, String path) {
        this.printer = printer;
        this.state = new TaskState(Decoder.decodeText(path, "UTF-8"), BigInteger.ZERO, TaskState.State.NONE);
    }

    /**
     * Walks through a file tree and counts files
     */
    @Override
    public void run() {
        if (Validator.isPathValid(state.getPath())) {
            try {
                Files.walkFileTree(
                        Paths.get(state.getPath()), new FileCounter(state));
                if (!state.getTaskState().equals(TaskState.State.CANCELLED)) {
                    if (!state.getTaskState().equals(TaskState.State.NO_ACCESS))
                    state.setState(TaskState.State.COMPLETE);
                }
            } catch (IOException e) {
                System.err.println("Exception was occurred while FileVisitor walked through file tree");
            }
        } else {
            state.setState(TaskState.State.INVALID);
        }
        synchronized (printer) {
            printer.printTask(state);
        }
    }
}
