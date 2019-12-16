package ml.lubster.services.taskrunners;

import ml.lubster.tasks.FileCountTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * {@inheritDoc}
 */
public class FileCountRunner implements ITaskRunner {
    /**
     * ExecutorService to run new thread for the each path.
     */
    private final ExecutorService service;
    /**
     * Map for collecting the each task and its result.
     */
    private final Map<FileCountTask, Future<?>> tasks;
    /**
     *
     */
    private TaskParameters taskParameter;

    /**
     * Creates an instance of a class. Initialize {@link FileCountRunner#tasks}
     *
     */
    public FileCountRunner(TaskParameters taskParameters, ExecutorService service) {
        this.taskParameter = taskParameters;
        this.tasks = new HashMap<>(taskParameters.getPaths().size());
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        taskParameter.getPaths().forEach(path -> {
            FileCountTask task = new FileCountTask(taskParameter.getPrinter(), path);
            tasks.put(task, service.submit(task));
        });
        tasks.forEach((task, result) -> {
            try {
                result.get();
            } catch (InterruptedException e) {
                System.err.println("Interrupted Exception was occurred");
                e.getMessage();
                result.cancel(true);
            } catch (ExecutionException e) {
                System.err.println("Execution Exception was occurred");
                result.cancel(true);
                e.getMessage();
                e.printStackTrace();
            } catch (CancellationException e) {
                // nothing to do
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void interrupt() {
        tasks.values()
                .forEach(result -> result.cancel(true));
        try {
            service.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException while awaiting ExecutorService Termination");
        }
        service.shutdownNow();
    }
}
