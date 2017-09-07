package downloader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineReaderTest {

    private CommandLineReader commandLineReader;

    @Before
    public void setUp() {
        commandLineReader =
                new CommandLineReader(new String[]{
                        "-n", "10",
                        "-l", "2000000",
                        "-f", "/home/someone/file.txt",
                        "-o", "output"
                });
    }

    @Test
    public void getThreadsNumber() throws Exception {
        assertEquals(commandLineReader.getThreadsNumber(), 10);

        commandLineReader = new CommandLineReader(new String[]{});
        assertEquals(commandLineReader.getThreadsNumber(), 5);

        commandLineReader = new CommandLineReader(new String[]{"-n", "sdasdgsadg"});
        assertEquals(commandLineReader.getThreadsNumber(), 5);
    }


    @Test
    public void getBytesPerSecond() throws Exception {
        assertEquals(commandLineReader.getBytesPerSecond(), 2000000);

        commandLineReader = new CommandLineReader(new String[]{});
        assertEquals(commandLineReader.getBytesPerSecond(), 1000000);

        commandLineReader = new CommandLineReader(new String[]{"-c", "ajgasjgasg"});
        assertEquals(commandLineReader.getBytesPerSecond(), 1000000);
    }

    @Test
    public void getPathToFileLinks() {
        assertEquals(commandLineReader.getPathToFileLinks(), "/home/someone/file.txt");

        commandLineReader = new CommandLineReader(new String[]{"-f", "/dir/file"});
        assertEquals(commandLineReader.getPathToFileLinks(), "/dir/file");
    }

    @Test
    public void getOutputDirPath() {
        assertEquals(commandLineReader.getOutputDirPath(), "output");

        commandLineReader = new CommandLineReader(new String[]{"-o", "/home/output"});
        assertEquals(commandLineReader.getOutputDirPath(), "/home/output");
    }

}