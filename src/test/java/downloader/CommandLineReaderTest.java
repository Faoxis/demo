package downloader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineReaderTest {

    private CommandLineReader commandLineReader;

    @Before
    public void setUp() {
        commandLineReader =
                new CommandLineReader(new String[]{"-n", "10", "-s", "2000000"});
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

}