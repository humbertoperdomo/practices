package mx.naui.thread.producerConsumer;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CircularBufferTest {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();

    Buffer sharedLocation = new CircularBuffer(5);

    executorService.execute(new Producer(sharedLocation));
    executorService.execute(new Consumer(sharedLocation));

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);
  }
}