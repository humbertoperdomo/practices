package mx.naui.thread;

import java.util.Random;

// HiloEscritor.java: clase que implementa la interfaz Runnable y es la clase encargada
// de introducir nuevos valores al buffer.
public class HiloEscritor implements Runnable {
  private Buffer buffer;
  private int vueltas;

  public HiloEscritor(Buffer buffer, int vueltas) {
    this.buffer = buffer;
    this.vueltas = vueltas;
  }

  @Override
  public void run() {
    for(int i = 0; i < vueltas; i++) {
      int aDormir = this.calcularRandom(10);
      System.out.println("HiloEscritor: durmiendo " + aDormir + " segundos");
      try {
        Thread.sleep(1000 * aDormir);
      } catch(InterruptedException e) {
      }
      int dato = this.calcularRandom(100);
      System.out.println("HiloEscritor: escribiendo " + dato + " en el buffer");
      buffer.setValor(dato);
    }
  }

  private int calcularRandom(int maximo) {
    Random rand = new Random();
    return rand.nextInt(maximo);
  }
}