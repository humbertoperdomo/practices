package mx.naui.thread;

import java.io.*;

public class ProducerConsumerTest {

  public static void main(String[] args) throws InterruptedException {

    if (args.length == 0) {
      System.out.println("Usage: ProducerConsumerTest <Buffer'size size>");
      System.exit(0);
    } else if (args.length > 1) {
      System.out.println("Too many arguments.");
      System.out.println("Usage: ProducerConsumerTest <Buffer'size size>");
      System.exit(0);
    } else {
      CircularBuffer buffer = new CircularBuffer(Integer.parseInt(args[0])); // 10 chars
      Producer producer = new Producer(buffer, "Producer 1");
      Consumer consumer = new Consumer(buffer, "Consumer 1");
      producer.start();
      consumer.start();
    }
  }
}
