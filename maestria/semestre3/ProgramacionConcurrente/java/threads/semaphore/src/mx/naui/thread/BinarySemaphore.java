package mx.naui.thread;

public class BinarySemaphore extends Semaphore {
  public BinarySemaphore() {
    super();
  }

  public BinarySemaphore(int initial) {
    super((initial != 0) ? 1 : 0);
  }

  public BinarySemaphore(boolean initial) {
    super(initial ? 1 : 0);
  }

  public final synchronized void notifyToWakeup() {
    super.notifyToWakeup();
    if (value > 1) {
      value = 1;
    }
  }
}
