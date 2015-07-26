package Volatile;

import java.util.Scanner;

class Processor extends Thread {
  // volatile is used to prevent threads catching variables
  // when they don't change
  private volatile boolean running = true;
  public void run() {
    while (running) {
      System.out.println(Thread.currentThread().getName() + " - Hello");
      try{
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void shutdown() {
      running = false;
  }
}

public class App {
  public static void main(String[] args) {
    Processor p1 = new Processor();
    p1.start();
    Processor p2 = new Processor();
    p2.start();
    
    System.out.println("Press return to stop...");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();

    p1.shutdown();
  }
}
