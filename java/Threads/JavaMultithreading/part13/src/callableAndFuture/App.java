package callableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class App {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    
    // Also you can use a Callable instance instead of Runnable
    /*
    executor.submit(new Runnable() {
        @Override
        public void run() {
          Random random = new Random();
          int duration = random.nextInt(4000);
          System.out.println("Starting...");
          try {
            Thread.sleep(duration);
          } catch (InterruptedException ie) {
          }
          System.out.println("Finished.");
        }
        });
    */
    //Future<Integer> future = executor.submit(new Callable<Integer>() {
    // *** Using this, you can take advantage of Future pros without returning nothing
    Future<?> future = executor.submit(new Callable<Void>() {
        // public Integer call() throws Exception {
        // *** Using this, you can take advantage of Future pros without returning nothing
        @Override
        public Void call() throws Exception {
          Random random = new Random();
          int duration = random.nextInt(4000);
          
          // This section is just for demostrate that when an exception
          // occurs, the get() method returns the exception.
          if (duration > 2000)
            throw new IOException("Sleeping for too long.");

          System.out.println("Starting...");

          try {
            Thread.sleep(duration);
          } catch(InterruptedException ie) {
          }

          System.out.println("Finished.");

          //return duration;
          // *** Using this, you can take advantage of Future pros without returning nothing
          return null;
        }
        });
    executor.shutdown();
    try {
      System.out.println("Result is: "+ future.get());
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    } catch (ExecutionException ee) {
      ee.printStackTrace();
    }
  }
}
