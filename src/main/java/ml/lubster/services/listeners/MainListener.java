package ml.lubster.services.listeners;

/**
 * Print to the console information about main progress.
 */
public class MainListener {
        /**
         * Prints to the console information about not existing a file.
         *
         * @param path path to a file.
         */
        public void onNonExistentFile(String path) {
            System.out.printf("'%s' is not found\n", path);
        }

        /**
         * Prints to the console information about arguments of the program.
         */
        public void onNotSufficientParameters() {
            System.out.println("First argument: config file must be each line is a find path\n"
                    + "Second argument: an output file path with final search result");
        }

        /**
         * Prints to the console information about the program completion.
         */
        public void onComplete() {
            System.out.println("Exit");
        }

        /**
         * Print notification when counting process starts. Prints headers of a result table.
         */
        public void onStartCounting() {
            System.out.println("Press Esc to cancel");
            System.out.println("Start files counting ...");
        }

        /**
         * Print notification when counting process is cancelled.
         */
        public void onAccessDenial(String path) {
            System.out.printf("Was to deny access to '%s'\n", path);
        }
    }