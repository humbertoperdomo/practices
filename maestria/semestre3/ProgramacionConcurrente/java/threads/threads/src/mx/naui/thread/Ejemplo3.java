package mx.naui.thread;

// Ejemplo3.java: clase que prueba las clases HiloLector e HiloEscritor
public class Ejemplo3 {
  private static final int VUELTAS = 5;
  public static void main(String[] args) {
    Buffer buffer = new Buffer();
    Thread hiloLector = new Thread(new HiloLector(buffer, VUELTAS));
    Thread hiloExcritor = new Thread(new HiloEscritor(buffer, VUELTAS));
    hiloLector.start();
    hiloExcritor.start();
  }
}