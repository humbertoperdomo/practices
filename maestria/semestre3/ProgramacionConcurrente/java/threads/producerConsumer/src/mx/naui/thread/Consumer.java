package mx.naui.thread;

import java.util.Random;

public class Consumer extends Thread {
  private CircularBuffer buffer;

  public Consumer(CircularBuffer buffer, String threadName) {
    this.buffer = buffer;
    setName(threadName);
  }

  public void run() {
    Random random = new Random();
    
    while(true){
      try {
        Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
      char ch = consume();
    }
  }

  public char consume() {
    return buffer.doGet(getName());
  }
}