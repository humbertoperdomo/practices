package mx.naui.thread.producerConsumer;

import java.security.SecureRandom;

public class Producer implements Runnable {
  private static final SecureRandom generator = new SecureRandom();
  private final Buffer sharedLocation; // reference to shared object

  // constructor 
  public Producer(Buffer sharedLocation) {
    this.sharedLocation = sharedLocation;
  }

  // store  values from 1 to 10 in sharedLocation
  @Override
  public void run() {
    //int sum = 0;
    for (int count = 33; count <= 57; count++) {
      try { // sleep 0 to 3 seconds, then place value uin Buffer
        Thread.sleep(generator.nextInt(3000)); // random sleep
        sharedLocation.blockingPut((char)count); // set value in Buffer
        //sum += count;  
        //System.out.printf("\t%2d%n", sum); // UnsynchronizedBufferTest
      } catch (InterruptedException exception) {
        Thread.currentThread().interrupt();
      }
    }
    System.out.printf("Producer done producing%nTerminating Producer%n");
  }

}