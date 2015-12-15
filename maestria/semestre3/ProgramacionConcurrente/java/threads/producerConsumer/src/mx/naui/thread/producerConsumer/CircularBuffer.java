package mx.naui.thread.producerConsumer;

public class CircularBuffer implements Buffer {
  private final char[] buffer;
  private int bMaxSize;// max buffer size
  private int occupiedCells = 0; // count number of buffers used
  private int writeIndex = 0; // index of next element to write to
  private int readIndex = 0; // index of next element to read

  public CircularBuffer(int size) {
    bMaxSize = size;
    buffer =  new char[size];
  }

  // place value into buffer
  @Override
  public synchronized void blockingPut(char value) throws InterruptedException {
    // wait while buffer has space available, then write value;
    // while no empty locations, place thread in waiting state
    while (occupiedCells == bMaxSize) {
      System.out.printf("Buffer is full. Producer waits.%n");
      wait(); // wait until a buffer cell is free
    }

    buffer[writeIndex] = value; // set new buffer value

    // update circular write index
    writeIndex = (writeIndex + 1) % bMaxSize;

    ++occupiedCells; // one more buffer cell is full
    displayState("Producer writes " + value);
    notifyAll(); // notify threads waiting to read from buffer
  }

  // return value from buffer
  @Override
  public synchronized char blockingGet() throws InterruptedException {
    // wait until buffer has data, then read value;
    // while no data to read, place thread in waiting state
    while (occupiedCells == 0) {
      System.out.printf("Buffer is empty. Consumer waits.%n");
      wait(); // wait until a buffer cell is filled
    }

    char readValue = buffer[readIndex]; // read value from buffer

    // update circular read index
    readIndex = (readIndex + 1) % bMaxSize;

    --occupiedCells; // one fewer buffer cells are occupied
    displayState("Consumer reads " + readValue);
    notifyAll(); // notify threads waiting to write to buffer

    return readValue;
  }

  // display current operation and buffer state
  public synchronized void displayState(String operation) {
    // output operation and number of occupied buffer cells
    System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: " , occupiedCells, "buffer cells: ");

    for (char value : buffer)
      System.out.printf("  %c  ", value); // output values in buffer

    System.out.printf("%n              ");

    for (int i = 0; i < bMaxSize; i++)
      System.out.printf("---- ");

    System.out.printf("%n              ");

    for (int i = 0; i < bMaxSize; i++) {
      if (i == writeIndex && i == readIndex)
        System.out.print(" WR"); // both write and read index
      else if (i == writeIndex)
        System.out.print(" W   "); // just write index
      else if (i == readIndex)
        System.out.print(" R   ");  // just read index
      else
        System.out.printf("     ");
    }

    System.out.printf("%n");
  }

}