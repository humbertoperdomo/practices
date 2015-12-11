package mx.naui.thread;

import java.util.Random;

public class ProducerA implements Runnable {
  private BufferAcotado bufferAcotado;
  
  public ProducerA(BufferAcotado bufferAcotado) {
    this.bufferAcotado = bufferAcotado;
  }

  public void run() {
    Random random = new Random();
    while(true) {
      System.out.println("Character : '" + produce() + "' inserted.");
      bufferAcotado.printq();

      try {
        Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
  
  public char produce() {
    char ch = getRandomChar();

    bufferAcotado.put(ch);

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