package mx.naui.thread;

public class CountingSemaphore extends Semaphore {
  public CountingSemaphore() {
    super();
  }

  public CountingSemaphore(int initial) {
    super(initial);
  }
}
