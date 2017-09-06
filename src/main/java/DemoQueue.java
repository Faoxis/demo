import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DemoQueue {

    private static BlockingQueue<String> queue =
        new PriorityBlockingQueue<>();

    public static void main(String[] args) {
        putToQueue();
        getFromQueue();
    }

    private static void putToQueue() {
        new Thread(() -> {
            for (int i = 0; i < 100; ++i) {
                queue.offer(String.valueOf(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static void getFromQueue() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    System.out.println("yep!");
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
