package WaitAndNotify;

public class App {
  public static void main(String[] args) throws InterruptedException {
    Processor processor = new Processor();
    
    Thread t1 = new Thread (new Runnable() {
      @Override
      public void run() {
        try {
          processor.produce();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
    });
    
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.comsumer();
        } catch (InterruptedException ie) {
          ie.printStackTrace()
        }
      }
    });
    
    t1.start();
    t2.start();
  }
}

