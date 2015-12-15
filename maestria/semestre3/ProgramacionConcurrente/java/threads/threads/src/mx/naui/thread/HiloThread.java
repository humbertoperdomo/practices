package mx.naui.thread;

// HiloThread.java: clase que hereda de la clase Thread
public class HiloThread extends Thread {
  @Override
  public void run() {
    System.out.println("Hola, soy un hilo " + Thread.currentThread().getId() + " y heredo de Thread");
    System.out.println("Terminé");
  }
}