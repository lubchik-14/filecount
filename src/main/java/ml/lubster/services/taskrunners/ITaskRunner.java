package ml.lubster.services.taskrunners;

import ml.lubster.services.printers.ITaskPrinter;

import java.util.List;

/**
 * Deal with tasks.
 */
public interface ITaskRunner {
    /**
     * Start work with tasks.
     */
    void run();

    /**
     * Actions when work was interrupted
     */
    void interrupt();

    /**
     * Class describes the necessary parameters for each task.
     */
    class TaskParameters {
        /**
         * Printer to print the state of the tasks.
         */
        private ITaskPrinter printer;
        /**
         * List of path.
         */
        private List<String> paths;

        /**
         * Creates an instance of the class.
         *
         */
        public TaskParameters(ITaskPrinter printer, List<String> paths) {
            this.printer = printer;
            this.paths = paths;
        }

        public ITaskPrinter getPrinter() {
            return printer;
        }

        public List<String> getPaths() {
            return paths;
        }
    }
}
