package mx.naui.threads;

public class Counter {
  private Lock lock = new Lock();
  private int count = 0;

  public int inc() {
    lock.lock();
    try {
      int newCount = ++count;
      return newCount;
    } finally {
      lock.unlock();
    }
  }
}
