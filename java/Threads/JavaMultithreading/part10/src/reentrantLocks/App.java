package reentrantLocks;

public class App {
  public static void main(String[] args) throws Exception {
    Runner runner = new Runner();

    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
          runner.firstThread();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
      });

    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            runner.secondThread();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
      });
    
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    runner.finished();
  }
}
