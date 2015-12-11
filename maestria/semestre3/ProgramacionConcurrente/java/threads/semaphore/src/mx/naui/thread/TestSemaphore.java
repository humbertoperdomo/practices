package mx.naui.thread;

public class TestSemaphore implements Runnable {
  @Override
  public void run() {
    Connection.getInstance().connect(Thread.currentThread().getName());
  }

  public static void main(String[] args) throws Exception {
    Thread t1 = new Thread(new TestSemaphore());
    Thread t2 = new Thread(new TestSemaphore());
    Thread t3 = new Thread(new TestSemaphore());
    Thread t4 = new Thread(new TestSemaphore());
    Thread t5 = new Thread(new TestSemaphore());
    Thread t6 = new Thread(new TestSemaphore());
    Thread t7 = new Thread(new TestSemaphore());
    Thread t8 = new Thread(new TestSemaphore());
    Thread t9 = new Thread(new TestSemaphore());
    Thread t10 = new Thread(new TestSemaphore());
    Thread t11 = new Thread(new TestSemaphore());
    Thread t12 = new Thread(new TestSemaphore());
    Thread t13 = new Thread(new TestSemaphore());
    Thread t14 = new Thread(new TestSemaphore());
    Thread t15 = new Thread(new TestSemaphore());
    Thread t16 = new Thread(new TestSemaphore());
    Thread t17 = new Thread(new TestSemaphore());
    Thread t18 = new Thread(new TestSemaphore());
    Thread t19 = new Thread(new TestSemaphore());
    Thread t20 = new Thread(new TestSemaphore());
    Thread t21 = new Thread(new TestSemaphore());


    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
    t7.start();
    t8.start();
    t9.start();
    t10.start();
    t11.start();
    t12.start();
    t13.start();
    t14.start();
    t15.start();
    t16.start();
    t17.start();
    t18.start();
    t19.start();
    t20.start();
    t21.start();
  }
}
