package Synchronized;

public class App {
  private int count = 0;

  public synchronized void increment() {
    count++;
  }

  public static void main(String[] args) {
    App app = new App();
    app.doWork();
  }

  public void doWork() {
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        for(int i = 0; i < 10000; i++) {
          // it's needed to avoid that any other thread 
          // modifies count while this thread is working with it
          increment();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        for(int i = 0; i < 10000; i++) {
          increment();
        }
      }
    });
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
     
    System.out.println("Count is: " + count);
  }
}
