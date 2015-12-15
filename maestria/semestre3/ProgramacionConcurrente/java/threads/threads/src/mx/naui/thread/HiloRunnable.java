package mx.naui.thread;

// HiloRunnable.java: clase que implementa la interfaz Thread
public class HiloRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println("Hola, soy el hilo " + Thread.currentThread().getId() + " e implemento Runnable");
    System.out.println("Terminé");
  }
}