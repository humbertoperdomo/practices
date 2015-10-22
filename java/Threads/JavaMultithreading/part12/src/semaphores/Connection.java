package semaphores;

import java.util.concurrent.Semaphore;

public class Connection {
  private static Connection instance = new Connection();
  private int connections = 0;
  private Semaphore sem = new Semaphore(10);

  private Connection() {
  }

  public static Connection getInstance(){
    return instance;
  }

  public void connect() {
    try {
      sem.acquire();
    } catch(InterruptedException ie) {
    }
    
    try {
      doConnect();
    } finally {
      sem.release();
    }
  }

  public void doConnect() {
    synchronized(this) {
      connections++;
    }
    
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

    synchronized(this){
      connections--;
      System.out.println("Current connections: " + connections);
    }
  }
}
