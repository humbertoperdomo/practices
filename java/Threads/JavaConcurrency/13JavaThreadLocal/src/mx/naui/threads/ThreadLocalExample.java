package mx.naui.threads;

public class ThreadLocalExample {
  public static class MyRunnable implements Runnable {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    @Override
    public void run() {
      threadLocal.set((int) (Math.random() * 100D));
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ie) {
      }
      System.out.println(threadLocal.get());
    }
  }

  public static void main(String[] args) {
    MyRunnable shareRunnableInstance = new MyRunnable();
    Thread thread1 = new Thread(shareRunnableInstance);
    Thread thread2 = new Thread(shareRunnableInstance);

    thread1.start();
    thread2.start();

    try{
      thread1.join(); // wait for thread1 to terminate
      thread2.join(); // wait for thread2 to terminate
    } catch (InterruptedException ie) {
    }
  }
}
