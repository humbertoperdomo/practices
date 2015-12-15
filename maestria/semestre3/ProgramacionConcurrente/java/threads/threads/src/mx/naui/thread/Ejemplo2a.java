package mx.naui.thread;

// Ejemplo2a.java: clase que crea 2 hilos, los cuales utilizan una sola
// instancia de la clase Contador.
public class Ejemplo2a {
  public static void main(String[] args) {
    Contador contador = new Contador();
    Thread hilo1 = new Thread(new Hilo(contador));
    Thread hilo2 = new Thread(new Hilo(contador));
    hilo1.start();
    hilo2.start();
  }
}


// Sin importar que los 2 hilos trabajen con una sola clase Contador
// el resultado final es correcto, esto gracias a la implementación 
// del bloque synchronized que garantiza la exclusión mutua.