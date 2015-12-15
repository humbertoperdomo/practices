package mx.naui.thread;

import java.util.Random;

public class Hilo implements Runnable {
  private Contador contador;

  public Hilo(Contador contador) {
    this.contador = contador;
  }

  @Override 
  public void run() {
    Random rand = new Random();
    int cantidad = 0;
    for(int i = 0; i < 10; i++) {
      cantidad = rand.nextInt(100);
      System.out.println("Hilo " + Thread.currentThread().getId() + " incrementa en " + cantidad + " el contador");
      contador.incrementar(cantidad);
    }
  }
}