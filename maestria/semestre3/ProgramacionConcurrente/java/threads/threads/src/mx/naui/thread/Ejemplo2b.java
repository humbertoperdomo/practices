package mx.naui.thread;

// Ejemplo2b.java: clase que crea 2 hilos, y cada hilo utiliza su propia
// instancia de la clase Contador. 
public class Ejemplo2b {
  public static void main(String[] args) {
    Contador contador1 = new Contador();
    Contador contador2 = new Contador();
    Thread hilo1 = new Thread(new Hilo(contador1));
    Thread hilo2 = new Thread(new Hilo(contador2));
    hilo1.start();
    hilo2.start();
  }
}