package downloader;

public class CommandLineReader {

    private static int DEFAULT_THREADS_NUMBER = 5;
    private static int DEFAULT_BYTES_NUMBER_PER_SECOND = 1000000;

    private String[] args;
    private int threadsNumber;
    private int bytesPerSecond;
//    private String

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
            } else if (args[i].equals("-s") && i < args.length - 1) {
                try {
                    bytesPerSecond = Integer.valueOf(args[i + 1]);
                } catch (NumberFormatException ignored) { }
            } else if (args[i].equals("-f") && i < args.length - 1) {
            }
        }

        if (threadsNumber < 1) threadsNumber = DEFAULT_THREADS_NUMBER;
        if (bytesPerSecond < 1) bytesPerSecond = DEFAULT_BYTES_NUMBER_PER_SECOND;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public int getBytesPerSecond() {
        return bytesPerSecond;
    }
}
