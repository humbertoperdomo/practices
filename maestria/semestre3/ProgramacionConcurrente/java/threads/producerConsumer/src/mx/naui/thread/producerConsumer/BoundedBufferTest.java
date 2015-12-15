package mx.naui.thread.producerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BoundedBufferTest {
  public static void main(String[] args) throws InterruptedException {
    // create new thread pool with two threads
    ExecutorService executorService = Executors.newCachedThreadPool();

    // create BlockingBuffer to store chars
    Buffer sharedLocation = new BoundedBuffer(10);

    executorService.execute(new Producer(sharedLocation));
    executorService.execute(new Consumer(sharedLocation));

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);

  }
}