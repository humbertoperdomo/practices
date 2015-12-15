package mx.naui.thread;

// HiloLector.java: clase que implementa la interfaz Runnable y se encarga de
// leer los datos que están almacenados en el buffer.
public class HiloLector implements Runnable {
  private Buffer buffer;
  private int vueltas;

  public HiloLector(Buffer buffer, int vueltas) {
    this.buffer = buffer;
    this.vueltas = vueltas;
  }

  @Override
  public void run() {
    for(int i = 0; i < vueltas; i++) {
      System.out.println("HiloLector: esperando por el dato");
      int dato = buffer.getValor();
      System.out.println("HiloLector: dato leido del buffer = " + dato);
    }
  }
}