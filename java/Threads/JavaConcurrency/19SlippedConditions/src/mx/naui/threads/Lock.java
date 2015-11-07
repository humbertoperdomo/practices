package mx.naui.threads;

public class Lock {
  private boolean isLocked = true;

  public void lock() {
    synchronized(this) {
      while(isLocked) {
        try {
          this.wait();
        } catch(InterruptedException e) {
        }
      }
      isLocked = true;
    }
  }

  public synchronized void unlock() {
    isLocked = false;
    this.notify();
  }
}
