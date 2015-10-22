package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) throws Exception {
    // Semaphore is an object that maintains a count

    // Create a masive bunch of connections
    ExecutorService executor = Executors.newCachedThreadPool();
    
    for(int i = 0; i < 200; i++){
      executor.submit(new Runnable() {
          @Override
          public void run() {
            Connection.getInstance().connect();
          }
      });
    }
    executor.shutdown();

    executor.awaitTermination(1, TimeUnit.DAYS);
  }
}
