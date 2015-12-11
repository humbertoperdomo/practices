package mx.naui.thread;

import java.io.*;

public class ProducerConsumerTestA {

  public static void main(String[] args) throws InterruptedException {
    BufferAcotado bufferAcotado = new BufferAcotado(10); // 10 chars
    ProducerA producer = new ProducerA(bufferAcotado);
    ConsumerA consumer = new ConsumerA(bufferAcotado);

    Thread t1 = new Thread(producer);
    Thread t2 = new Thread(consumer);

    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }
}
