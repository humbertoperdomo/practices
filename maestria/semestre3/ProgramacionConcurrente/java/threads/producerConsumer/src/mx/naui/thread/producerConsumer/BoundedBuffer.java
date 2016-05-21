package mx.naui.thread.producerConsumer;

// BoundedBuffer access to a shared character using the Lock and Condition
// interfaces
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BoundedBuffer implements Buffer {
  // Lock to control synchronization with this buffer
  private final Lock accessLock = new ReentrantLock();

  // conditions to control reading and writing
  private final Condition canWrite = accessLock.newCondition();
  private final Condition canRead = accessLock.newCondition();

  private char[] buffer; // shared by producer and consumer threads
  private int bMaxSize;// max buffer size
  private int frontPointer = 0;  // front pointer
  private int rearPointer = 0;  // rear pointer
  private int occupiedCells = 0;  // size of buffer
  //private boolean occupied = false; // whether buffer is occupied


  public BoundedBuffer(int size) {
    bMaxSize = size;
    frontPointer = 0;
    rearPointer = 0;
    occupiedCells = 0;
    buffer = new char[bMaxSize];
  }

  // place char value into buffer
  public void blockingPut(char value) throws InterruptedException {
    accessLock.lock(); // lock this object

    // output thread information and buffer information, then wait
    try {
      // while buffer is full, place thread in waiting state
      while (occupiedCells == bMaxSize) {
        System.out.println("Producer tries to write.");
        System.out.println("Buffer full. Producer waits.");
        canWrite.await(); // wait until buffer has space
      }

      buffer[rearPointer] = value; // set new buffer value
      rearPointer = (rearPointer + 1)%bMaxSize;

      // indicate producer that one more cell is occupied
      occupiedCells++;
      displayState("Producer writes " + value);

      // signal any threads waiting to read from buffer
      canRead.signalAll();
    } finally {
      accessLock.unlock(); // unlock this object
    }
  }

  // return value from buffer
  public char blockingGet() throws InterruptedException {
    char readValue = '\0'; // initialize value read from buffer
    accessLock.lock(); // lock this object

    // output thread information and buffer information, then wait
    try {
      // if there is no data to read, place thread in waiting state
      while(occupiedCells == 0) {
        System.out.println("Consumer tries to read.");
        System.out.println("Buffer empty. Consumer waits.");
        canRead.await();
      }

      readValue = buffer[frontPointer]; // read value from buffer
      frontPointer = (frontPointer + 1) % bMaxSize;
      // indicate that there is one more space in buffer
      occupiedCells--;
      displayState("Consumer reads " + readValue);
      // signal any threads waiting for space buffer
      canWrite.signalAll();
    } finally {
      accessLock.unlock(); // unlock this object
    }

    return readValue;
  }

  // display current operation and buffer state
  public void displayState(String operation) {
    // output operation and number of occupied buffer cells
    try {
      accessLock.lock(); // lock this object
      System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: " , occupiedCells, "buffer cells: ");

      for (char value : buffer)
        System.out.printf("  %c  ", value); // output values in buffer

      System.out.printf("%n              ");

      for (int i = 0; i < bMaxSize; i++)
        System.out.printf("---- ");

      System.out.printf("%n              ");

      for (int i = 0; i < bMaxSize; i++) {
        if (i == frontPointer && i == rearPointer)
          System.out.print(" FR"); // both write and read index
        else if (i == rearPointer)
          System.out.print(" R   "); // just write index
        else if (i == frontPointer)
          System.out.print(" F   ");  // just read index
        else
          System.out.printf("     ");
      }

      System.out.printf("%n");
    } finally {
      accessLock.unlock(); // unlock this object
    }
  }
}