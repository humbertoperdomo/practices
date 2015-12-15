package mx.naui.thread.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {
  private final ArrayBlockingQueue<Character> buffer; // shared buffer

  public BlockingBuffer() {
    this.buffer = new ArrayBlockingQueue<Character>(1);
  }

  // place value into buffer
  @Override
  public void blockingPut(char value) throws InterruptedException {
    buffer.put(value);  // place value in buffer
    System.out.printf("%s%c\t%s%d%n", "Producer writes ", value, "Buffer cells occupied: ", buffer.size());
  }

  // return value from buffer
  @Override
  public char blockingGet() throws InterruptedException {
    char readValue = buffer.take(); // remove value from buffer
    System.out.printf("%s %c\t%s%d%n", "Consumer reads ", readValue, "Buffer cells occupied: ", buffer.size());

    return readValue;
  }
  
}