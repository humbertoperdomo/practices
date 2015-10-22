package mx.naui.threads;

public class MyWaitNotify {
  MonitorObject myMonitorObject = new MonitorObject();

  public void doWait() { 
    synchronized(myMonitorObject) {
      try {
        myMonitorObject.wait();
      } catch (InterruptedException ie) {
      }
    }
  }

  public void doNotify() {
    synchronized(myMonitorObject) {
      myMonitorObject.notify();
    }
  }
}
