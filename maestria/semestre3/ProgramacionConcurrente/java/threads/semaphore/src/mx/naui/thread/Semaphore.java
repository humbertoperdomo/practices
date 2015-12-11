package mx.naui.thread;

public abstract class Semaphore {
  protected int value = 0;
  protected int waitCount = 0;
  protected int notifyCount = 0;

  protected Semaphore() {
    value = 0;
  }

  protected Semaphore(int initial) {
    if (initial > 0) {
      value = initial;
    }
  }

  public synchronized void waitForNotify() {
    if (value <= waitCount) {
      waitCount++;
      try {
        do {
          wait();
        } while (notifyCount == 0);
      } catch (InterruptedException e) {
        notify();
      } finally {
        waitCount--;
      }
      notifyCount--;
    }
    value--;
  }

  public synchronized void notifyToWakeup() {
    value++;
    if (waitCount > notifyCount) {
      notifyCount++;
      notify();
    }
  }
}
