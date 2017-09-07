package downloader;

public class CommandLineReader {

    private static final int DEFAULT_THREADS_NUMBER = 5;
    private static final int DEFAULT_BYTES_NUMBER_PER_SECOND = 1000000;
    private static final String DEFAULT_PATH_FILE_LINKS = ".";
    private static final String DEFAOUT_OUTPUT_PATH = ".";


    private String[] args;
    private int threadsNumber;
    private int bytesPerSecond;
    private String pathToFileLinks;
    private String outputDirPath;

    public CommandLineReader(String[] args) {
        this.args = args;
        parsArgs();
    }

    private void parsArgs() {
        threadsNumber = -1;
        bytesPerSecond = -1;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-n") && i < args.length - 1) {
                try {
                    threadsNumber = Integer.valueOf(args[i + 1]);
                } catch (NumberFormatException ignored) { }
            } else if (args[i].equals("-l") && i < args.length - 1) {
                try {
                    bytesPerSecond = Integer.valueOf(args[i + 1]);
                } catch (NumberFormatException ignored) { }
            } else if (args[i].equals("-f") && i < args.length - 1) {
                pathToFileLinks = args[i + 1];
            } else if (args[i].equals("-o") && i < args.length - 1) {
                outputDirPath = args[i + 1];
            }
        }

        if (threadsNumber < 1) threadsNumber = DEFAULT_THREADS_NUMBER;
        if (bytesPerSecond < 1) bytesPerSecond = DEFAULT_BYTES_NUMBER_PER_SECOND;
        if (pathToFileLinks == null) pathToFileLinks = DEFAULT_PATH_FILE_LINKS;
        if (outputDirPath == null) outputDirPath = DEFAOUT_OUTPUT_PATH;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public int getBytesPerSecond() {
        return bytesPerSecond;
    }

    public String getPathToFileLinks() {
        return this.pathToFileLinks;
    }

    public String getOutputDirPath() {
        return this.outputDirPath;
    }

}
