package downloader;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Downloader {

    private static String FILE_WITH_LINKS = "/home/faoxis/projects/downloader/links.txt";
    private static String SAVE_DIR = "/home/faoxis/projects/downloader/to/";

    public static void main(String[] args) throws FileNotFoundException {
        CommandLineReader commandLineReader = new CommandLineReader(args);

        ExecutorService executorService =
                Executors.newFixedThreadPool(commandLineReader.getThreadsNumber());

        LinksFromFileService.getLinks(FILE_WITH_LINKS).forEach(link -> {
            executorService.submit(new FileSaver(link, SAVE_DIR, commandLineReader.getBytesPerSecond()));
        });

        executorService.shutdown();
    }

}
