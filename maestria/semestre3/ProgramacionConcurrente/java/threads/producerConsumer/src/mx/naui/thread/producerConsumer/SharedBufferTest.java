package mx.naui.thread.producerConsumer;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SharedBufferTest {
  public static void main(String[] args) throws InterruptedException {
    // create new thread pool with two threads
    ExecutorService executorService = Executors.newCachedThreadPool();

    // create UnsynchronizedBuffer to store chars
    Buffer sharedLocation = new UnsynchronizedBuffer();

    System.out.printf("Action\t\tValue\tSum of Produced\tSum of Consumed%n");
    System.out.printf("------\t\t-----\t---------------\t---------------%n%n");

    // execute the Producer and Consumer, giving each
    // access to the sharedLocation
    executorService.execute(new Producer(sharedLocation));
    executorService.execute(new Consumer(sharedLocation));

    executorService.shutdown(); // terminate app when tasks complete
    executorService.awaitTermination(1, TimeUnit.MINUTES);
  }
}