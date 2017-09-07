package downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileSaver implements Runnable {

    private String link;
    private String pathToSave;
    private int speed;

    public FileSaver(String link, String pathToSave, int speed) {
        this.link = link;
        this.pathToSave = pathToSave;
        this.speed = speed;
    }

    @Override
    public void run() {

        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {

            // path to download
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();

            // path to save
            String[] linkParts = link.split("/");
            String fileName = linkParts[linkParts.length - 1];
            outputStream = new FileOutputStream(pathToSave + fileName);

            byte buffer[] = new byte[speed];
            while (inputStream.available() > 0) {

                long startMillis = System.currentTimeMillis();
                int readBytesCounter = inputStream.read(buffer);
                outputStream.write(buffer, 0, readBytesCounter);
                long endMillis = System.currentTimeMillis();

                long downloadTime = endMillis - startMillis;
                if (downloadTime < 1000) {
                    Thread.sleep(1000 - downloadTime);
                }

            }

            System.out.println(String.format("%s is done!", fileName));
            inputStream.close();
            outputStream.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
