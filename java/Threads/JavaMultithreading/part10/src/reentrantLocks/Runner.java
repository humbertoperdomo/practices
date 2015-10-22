package reentrantLocks;
import java.util.concurrent.locks.Lock;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


public class Runner {
  private int count = 0;
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();
  
  private void increment() {
    for (int i = 0; i < 10000; i++) {
      count++;
    }
  }
  public void firstThread() throws InterruptedException {
    lock.lock();
    System.out.println("Waiting...");
    condition.await(); // similar to the common wait from class Object
    System.out.println("Woken up!");

    try { // these try and finally statements are to ensure unlock execution
     increment();
    } finally {
     lock.unlock();
    }
  }

  public void secondThread() throws InterruptedException {
    Thread.sleep(1000);
    lock.lock();
    
    System.out.println("Press the return key!");
    new Scanner(System.in).nextLine();
    System.out.println("Got return key!");

    condition.signal(); // similar to the common notifyAll from class Object
    try {
      increment();
    } finally {
      lock.unlock();
    }
  }

  public void finished() {
    System.out.println("Count is: " + count);
  }
}
