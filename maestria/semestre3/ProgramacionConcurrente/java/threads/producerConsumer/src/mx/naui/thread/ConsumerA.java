package mx.naui.thread;

import java.util.Random;

public class ConsumerA implements Runnable {
  private BufferAcotado bufferAcotado;
  private int numItems = 0;
  private Random random;

  public ConsumerA(BufferAcotado bufferAcotado) {
    this.bufferAcotado = bufferAcotado;
    random = new Random();
  }

  public ConsumerA(BufferAcotado bufferAcotado, int numItems) {
    this.bufferAcotado = bufferAcotado;
    this.numItems = numItems;
    random = new Random();
  }

  public void run() {
    
    char ch = '\0';

    if (numItems <= 0) {
      while(true){
        try {
          Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
          ch = bufferAcotado.get();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }

        //System.out.println("Character : '" + consume() + "' consumed.");
      }
    } else {
      for (int i = 0; i < numItems; i++) {
        try {
          Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
          ch = bufferAcotado.get();
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        }
      }
      System.out.printf("%n%s%n", "Terminating Consumer");
    }
  }
}