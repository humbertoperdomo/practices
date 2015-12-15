package mx.naui.thread.producerConsumer;

// SynchronizedBuffer.java
// Synchronizing access to shared mutable data using Object
// method wait and notifyAll.
public class SynchronizedBuffer implements Buffer {
  private char buffer = '\0'; // shared by producer and consumers threads
  private boolean occupied = false;

  
  // place value into buffer
  @Override
  public synchronized void blockingPut(char value) throws InterruptedException {
    // while there are no empty locations, place thread in waiting state
    while (occupied) {
      // output thread information and buffer information, then waiti
      System.out.println("Producer tries to write.");
      displayState("Buffer full. Producer waits.");
      wait();
    }

    buffer = value; // set new buffer value

    // indicate producer cannot store another value
    // until consumer retrieves current buffer value
    occupied = true;

    displayState("Producer writes " + buffer);

    notifyAll(); // tell waiting thread(s) to enter runnable state
  } // end method blockingPut; releases lock on SynchronizedBuffer

  // return value from buffer
  @Override
  public synchronized char blockingGet() throws InterruptedException {
    // while no data to read, place thread in waiting state
    while(!occupied) {
      // output thread information and buffer information, then wait
      System.out.println("Consumer tries to read.");
      displayState("Buffer empty. Consumer waits.");
      wait();
    }

    // indicate that producer can store another value
    // because consumer just retrieved buffer value
    occupied = false;

    displayState("Consumer reads " + buffer);

    notifyAll();

    return buffer;
  }

  // display current operation and buffer state
  private synchronized void displayState(String operation) {
    System.out.printf("%-40s%c\t\t%b%n%n", operation, buffer, occupied);
  }
}