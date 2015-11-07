package mx.naui.threads;

/**
 * Fair Lock implementation without nested monitor lockout problem,
 * but with missed signals problem.
 */ 
public class FairLock {
  private boolean isLocked = false;
  private Thread lockingThread = null;
  private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

  public void lock() throws InterruptedException {
    QueueObject queueObject = new QueueObject();

    synchronized(this) {
      waitingThreads.add(queueObject);
    }

    boolean mustWait = true;
    while(mustWait) {
      synchronized(this) {
        mustWait = isLocked || waitingThreads.get(0) != queueObject;
        if(!mustWait) {
          waitingThreads.remove(queueObject);
          isLocked = true;
          lockingThread = Thread.currentThread();
          return;
        }
      }

      synchronized(queueObject) {
        if(mustWait) {
          try {
            queueObject.wait();
          } catch(InterruptedException e) {
            waitingThreads.remove(queueObject);
            throw e;
          }
        }
      }
    }
  }
}
