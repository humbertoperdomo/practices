package mx.naui.thread;

import java.util.Random;

public class ProducerA implements Runnable {
  private BufferAcotado bufferAcotado;
  private int numItems = 0;
  private Random random;
  
  public ProducerA(BufferAcotado bufferAcotado) {
    this.bufferAcotado = bufferAcotado;
    random = new Random();
  }

  public ProducerA(BufferAcotado bufferAcotado, int numItems) {
    this.bufferAcotado = bufferAcotado;
    this.numItems = numItems;
    random = new Random();
  }

  public void run() {
    

    if (numItems <= 0) {
      while(true) {
        try {
          Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
          bufferAcotado.put(getRandomChar());
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
    } else {
      for (int i = 0; i < numItems; i++) {
        try {
          Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
          bufferAcotado.put(getRandomChar());
        } catch (InterruptedException exception) {
          Thread.currentThread().interrupt();
        }
      }
      System.out.printf("Producer done producing%nTerminating Producer%n");
    }
  }

  private char getRandomChar() {
    return getRandomChar(33, 126);
  }

  private char getRandomChar(int min, int max) {
    Random random = new Random();
    return (char) (random.nextInt(max - min + 1) + min);
  }
}