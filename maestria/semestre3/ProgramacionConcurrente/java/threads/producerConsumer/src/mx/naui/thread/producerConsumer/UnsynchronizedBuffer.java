package mx.naui.thread.producerConsumer;

// UnsynchronizedBuffer.java
// Maintains the shared character that is accessed by a
// producer thread and a consumer thread
public class UnsynchronizedBuffer implements Buffer {
  private char buffer = '\0'; // shared by producer and consumer threads

  // place value into buffer
  @Override
  public void blockingPut(char value) throws InterruptedException {
    System.out.printf("Producer writes\t%c", value);
    buffer = value;
  }

  // return value from buffer
  @Override
  public char blockingGet() {
    System.out.printf("Consumer reads\t%c", buffer);
    return buffer;
  }
}