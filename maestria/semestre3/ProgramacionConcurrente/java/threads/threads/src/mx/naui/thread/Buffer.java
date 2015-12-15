package mx.naui.thread;

// Buffer.java: La clase Buffer muestra como puede hacerse para poner un hilo
// en espera hasta que otro hilo mande una señal que notifique que puede dejar
// esperar.
public class Buffer {
  private int valor;

  public Buffer() {
    valor = 0;
  }

  // El metodo getValor() siempre es obligado a esperar a que reciba
  // una notificación del metodo setValor, esto, una vez que el metodo
  // setValue haya asignado un nuevo valor.
  public synchronized int getValor() {
    try {
      wait();
    } catch (InterruptedException e){
    }

    return valor;
  }

  // Una vez que el metodo setValor genera un nuevo valor
  // manda una señal para que los hilos que están en espera
  // se activen he intenten obtener el lock.
  public synchronized void setValor(int valor) {
    this.valor = valor;
    notifyAll();
  }
}