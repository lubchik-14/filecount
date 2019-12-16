package ml.lubster;

import ml.lubster.services.converters.ConsoleTaskConverter;
import ml.lubster.services.converters.CsvTaskConverter;
import ml.lubster.services.listeners.*;
import ml.lubster.services.printers.ConsoleTaskPrinter;
import ml.lubster.services.printers.DelegateTaskPrinter;
import ml.lubster.services.printers.FileTaskPrinter;
import ml.lubster.services.printers.ITaskPrinter;
import ml.lubster.services.readers.FileReader;
import ml.lubster.services.readers.IReader;
import ml.lubster.services.taskrunners.FileCountRunner;
import ml.lubster.services.taskrunners.ITaskRunner;
import ml.lubster.services.Validator;
import ml.lubster.services.writers.FileLineWriter;
import ml.lubster.services.writers.IWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The main class of the console app.
 * The app counts all files in given directories and their subdirectories.
 * The first parameter is the source file with directories for counting.
 * The second parameter is the file with result of counting. The data is presented in the csv format.
 * {@author} Liubov Kruhla.
 * {@version} 1.0
 */
public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        MainListener mainListener = new MainListener();
        if (args.length < 2) {
            mainListener.onNotSufficientParameters();
            System.exit(0);
        }

        if (!Validator.isFileExist(args[0])) {
            mainListener.onNonExistentFile(args[0]);
            System.exit(0);
        }
        String inputPath = args[0];
        if (!Validator.isFileExist(args[1]) && !Validator.isAccessToFile(args[1])) {
            mainListener.onAccessDenial(args[1]);
            System.exit(0);
        }
        String outputPath = args[1];

        IReader<String> fileReader = new FileReader(inputPath);
        IWriter<String> fileWriter = new FileLineWriter(outputPath);
        ITaskPrinter printer = new DelegateTaskPrinter(
                new ConsoleTaskPrinter(
                        new ConsoleTaskConverter()),
                new FileTaskPrinter(
                        new CsvTaskConverter(";"), fileWriter));
        ITaskRunner.TaskParameters taskParameters = new ITaskRunner.TaskParameters(printer, fileReader.read());
        ITaskRunner countFileRunner = new FileCountRunner(taskParameters, service);
        KeyListener keyListener = new KeyListener(new EscListener(countFileRunner));
        keyListener.start();
        mainListener.onStartCounting();
        countFileRunner.run();
        keyListener.stop();
        fileWriter.close();
        try {
            service.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException while awaiting ExecutorService Termination");
        }
        mainListener.onComplete();
        service.shutdownNow();
    }
}
