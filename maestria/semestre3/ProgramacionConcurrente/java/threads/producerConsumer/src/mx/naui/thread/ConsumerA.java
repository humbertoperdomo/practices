package mx.naui.thread;

import java.util.Random;

public class ConsumerA implements Runnable {
  private BufferAcotado bufferAcotado;

  public ConsumerA(BufferAcotado bufferAcotado) {
    this.bufferAcotado = bufferAcotado;
  }

  public void run() {
    Random random = new Random();
    while(true){
      try {
        Thread.sleep((random.nextInt(1 - 0 + 1) + 0) * 1000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }

      System.out.println("Character : '" + consume() + "' consumed.");
    }
  }

  public char consume() {
    return bufferAcotado.get();
  }
}