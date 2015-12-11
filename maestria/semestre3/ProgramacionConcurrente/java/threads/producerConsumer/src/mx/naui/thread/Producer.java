package mx.naui.thread;

import java.util.Random;

public class Producer extends Thread {
  private CircularBuffer buffer;
  private Random random;
  
  public Producer(CircularBuffer buffer, String threadName) {
    this.buffer = buffer;
    setName(threadName);
    random = new Random();
  }

  @Override
  public void run() {
    while(true) {
      produce();
      buffer.printq();

      try {
        Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }

    }
  }
  
  public char produce() {
    char ch = getRandomChar();

    buffer.doPut(ch, getName());

    return ch;
  }

  private char getRandomChar() {
    return getRandomChar(33, 126);
  }

  private char getRandomChar(int min, int max) {
    Random random = new Random();
    return (char) (random.nextInt(max - min + 1) + min);
  }
}