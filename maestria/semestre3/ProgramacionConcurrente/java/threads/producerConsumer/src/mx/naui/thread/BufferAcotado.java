package mx.naui.thread;

// BufferAcotado.java: queue implementation
public class BufferAcotado {
  private int qMaxSize;// max queue size
  private int fp = 0;  // front pointer
  private int rp = 0;  // rear pointer
  private int bs = 0;  // size of queue
  private char[] q;    // actual queue
  private Semaphore semaphoreProd;
  private Semaphore semaphoreCons;

  public BufferAcotado(int size) {
    qMaxSize = size;
    fp = 0;
    rp = 0;
    bs = 0;
    q = new char[qMaxSize];
    semaphoreProd = new CountingSemaphore(size);
    semaphoreCons = new CountingSemaphore(0);
  }

  public char get() {
    semaphoreCons.waitForNotify();

    try {
      return doGet();
    } finally {
      semaphoreProd.notifyToWakeup();
    }
  }

  public synchronized char doGet() {
    bs--;
    fp = (fp + 1)%qMaxSize;
    return q[fp];
  }

  public void put(char c) {
    semaphoreProd.waitForNotify();

    try {
      doPut(c);
    } finally {
      semaphoreCons.notifyToWakeup();
    }
  }

  public synchronized void doPut(char c) {
    bs++;
    rp = (rp + 1)%qMaxSize;
    q[rp] = c;
  }

  public synchronized boolean emptyq() {
    return bs == 0;
  }

  public synchronized boolean fullq() {
    return bs == qMaxSize;
  }

  public synchronized char front() {
    return emptyq() ? '?' : q[(fp + 1) % qMaxSize];
  }
   
  public synchronized void printq() {
    System.out.print("Size: " + bs +
        ", rp: " + rp + ", fp: " + fp + ", q: ");
    for (int i = 0; i < qMaxSize; i++)
      System.out.print("q[" + i + "]=" 
          + q[i] + "; ");
    System.out.println();
  }
}
  