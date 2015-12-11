package mx.naui.thread;

public class Connection {
  private static Connection instance = new Connection();
  private int connections = 0;
  //private BinarySemaphore semaphore = new BinarySemaphore(1);
  private CountingSemaphore semaphore = new CountingSemaphore(3);

  private Connection() {
  }

  public static Connection getInstance(){
    return instance;
  }

  public void connect(String callingThread) {
    //try {
      semaphore.waitForNotify();
    //} catch(InterruptedException ie) {
    //}
    
    try {
      doConnect(callingThread);
    } finally {
      semaphore.notifyToWakeup();
    }
  }

  public void doConnect(String callingThread) {
    synchronized(this) {
      connections++;
      System.out.println(callingThread + " opened connection");
      System.out.println("Current connections: " + connections);
    }
    
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

    synchronized(this){
      System.out.println(callingThread + " closing connection");
      connections--;
    }
  }
}
