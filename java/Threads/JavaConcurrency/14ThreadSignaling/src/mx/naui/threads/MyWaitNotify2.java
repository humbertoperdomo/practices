package mx.naui.threads;

public class MyWaitNotify2 {
  MonitorObject myMonitorObject = new MonitorObject();
  boolean wasSignalled = false;

  public void doWait() {
   synchronized(myMonitorObject) {
     if(!wasSignalled) {
       try {
         myMonitorObject.wait();
       } catch (InterruptedException ie) {
       }
     }
     wasSignalled = false;
   }
  }

  public void doNotify() {
    synchronized(myMonitorObject) {
      wasSignalled = true;
      myMonitorObject.notify();
    }
  }
}
