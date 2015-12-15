package mx.naui.thread;


public class ProducerConsumerTestA {

  public static void main(String[] args) throws InterruptedException {
    BufferAcotado bufferAcotado = null;
    ProducerA producer = null;
    ConsumerA consumer = null;
    if (args.length == 0) {
      System.out.println("Usage: ProducerConsumerTestA <BUFFER SIZE> [NUMBER ITEMS]");
      System.exit(0);
    } else if (args.length == 1) {
      bufferAcotado = new BufferAcotado(Integer.parseInt(args[0]));
      producer = new ProducerA(bufferAcotado);
      consumer = new ConsumerA(bufferAcotado);
    } else if (args.length == 2) {
      bufferAcotado = new BufferAcotado(Integer.parseInt(args[0])); // 10 chars
      producer = new ProducerA(bufferAcotado, Integer.parseInt(args[1]));
      consumer = new ConsumerA(bufferAcotado, Integer.parseInt(args[1]));
    } else {
      System.out.println("Too many arguments.");
      System.out.println("Usage: ProducerConsumerTestA <BUFFER SIZE> [NUMBER ITEMS]");
      System.exit(0);
    }

    Thread t1 = new Thread(producer);
    Thread t2 = new Thread(consumer);

    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }
}
