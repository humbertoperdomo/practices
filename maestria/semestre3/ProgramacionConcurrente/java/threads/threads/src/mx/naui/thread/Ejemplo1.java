package mx.naui.thread;

// Ejemplo1.java: clase que prueba las clases HiloThread y HiloRunnable
public class Ejemplo1 {
  public static void main(String[] args) {
    HiloThread hilo1 = new HiloThread();
    Thread hilo2 = new Thread(new HiloRunnable());
    hilo1.start();
    hilo2.start();
  }
}