package mx.naui.thread.producerConsumer;

public interface  Buffer {
  public void blockingPut(char value) throws InterruptedException;

  public char blockingGet() throws InterruptedException;
}