package mx.naui.thread;

// BufferAcotado.java: buffer implementation
public class BufferAcotado {
  private char[] buffer;    // shared buffer
  private int bMaxSize;// max buffer size
  private int frontPointer = 0;  // front pointer
  private int rearPointer = 0;  // rear pointer
  private int occupiedCells = 0;  // size of buffer
  private Semaphore semaphoreBuff;
  private Semaphore semaphoreProd;
  private Semaphore semaphoreCons;

  public BufferAcotado(int size) {
    bMaxSize = size;
    frontPointer = 0;
    rearPointer = 0;
    occupiedCells = 0;
    buffer = new char[bMaxSize];
    semaphoreBuff = new BinarySemaphore(1);
    semaphoreProd = new CountingSemaphore(size);
    semaphoreCons = new CountingSemaphore(0);
  }

  public char get() {
    semaphoreCons.waitForNotify();
    semaphoreBuff.waitForNotify();
    try {
      return doGet();
    } finally {
      semaphoreBuff.notifyToWakeup();
      semaphoreProd.notifyToWakeup();
    }
  }

  public synchronized char doGet() {
    char readValue = buffer[frontPointer]; // read value from buffer
    frontPointer = (frontPointer + 1) % bMaxSize;
    occupiedCells--;
    displayState("Consumer reads " + readValue);
    return readValue;
  }

  public void put(char c) {
    semaphoreProd.waitForNotify();
    semaphoreBuff.waitForNotify();
    try {
      doPut(c);
    } finally {
      semaphoreBuff.notifyToWakeup();
      semaphoreCons.notifyToWakeup();
    }
  }

  public synchronized void doPut(char c) {
    buffer[rearPointer] = c;
    rearPointer = (rearPointer + 1)%bMaxSize;
    occupiedCells++;
    displayState("Producer writes " + c);
  }

  public synchronized boolean emptyb() {
    return occupiedCells == 0;
  }

  public synchronized boolean fullq() {
    return occupiedCells == bMaxSize;
  }

  public synchronized char front() {
    return emptyb() ? '?' : buffer[(frontPointer + 1) % bMaxSize];
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
  }
}
  