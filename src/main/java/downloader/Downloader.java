package downloader;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Downloader {

    public static void main(String[] args) throws FileNotFoundException {
        CommandLineReader commandLineReader = new CommandLineReader(args);

        ExecutorService executorService =
                Executors.newFixedThreadPool(commandLineReader.getThreadsNumber());

        System.out.println(commandLineReader.getOutputDirPath());
        System.out.println(commandLineReader.getBytesPerSecond());
        System.out.println(commandLineReader.getThreadsNumber());
        System.out.println(commandLineReader.getPathToFileLinks());

        LinksFromFileService.getLinks(commandLineReader.getPathToFileLinks()).forEach(link -> {
            executorService.submit(new FileSaver(link,
                    commandLineReader.getOutputDirPath(),
                    commandLineReader.getBytesPerSecond()));
        });

        executorService.shutdown();
    }

}
