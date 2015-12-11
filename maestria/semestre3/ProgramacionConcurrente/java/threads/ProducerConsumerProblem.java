import java.util.Random;
 
 
public class ProducerConsumerProblem {
 
   public static void main(String[] args) {     
      ProducerConsumerProblem pcp = new ProducerConsumerProblem();
      DataBuffer buffer = pcp.new DataBuffer(2);
       
      Producer p1 = pcp.new Producer(buffer, "Producer 1");
      Producer p2 = pcp.new Producer(buffer, "Producer 2");
      Producer p3 = pcp.new Producer(buffer, "Producer 3");
      Producer p4 = pcp.new Producer(buffer, "Producer 4");
      Producer p5 = pcp.new Producer(buffer, "Producer 5");
       
      Consumer c1 = pcp.new Consumer(buffer, "Consumer 1");
      Consumer c2 = pcp.new Consumer(buffer, "Consumer 2");
      Consumer c3 = pcp.new Consumer(buffer, "Consumer 3");
      Consumer c4 = pcp.new Consumer(buffer, "Consumer 4");
      Consumer c5 = pcp.new Consumer(buffer, "Consumer 5");
       
      p1.start();p2.start();p3.start();p4.start();p5.start();
      c1.start();c2.start();c3.start();c4.start();c5.start();
   }
    
   class DataBuffer{
                //Circular Queue implementation is used
                // Refernce www.cs.sfu.ca/CourseCentral/225/jmanuch/lec/5-1.ppt
      private int dataBuffer[];
      private int capacity;
       
      private int front = 0;
      private int currentSize = 0;
       
      public DataBuffer(int capacity) {
         this.capacity = capacity;
         dataBuffer = new int[capacity];
      }
       
      private boolean isEmpty(){
         return (currentSize == 0);
      }
       
      private boolean isFull(){
         return (currentSize == capacity);
      }
       
      public synchronized void produce(int data, String producerName){
         while(isFull()){
            try {
               wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         dataBuffer[(front+currentSize)%capacity] = data;
         System.out.println("Data "+ data +" produced by "+producerName);
         currentSize++;
         notifyAll();
      }
       
      public synchronized int consume(String consumerName){
         while(isEmpty()){
            try {
               wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         int data = dataBuffer[front];
         front = (front+1)%capacity;
         currentSize--;
         notifyAll();
         System.out.println("Data "+ data+" consumed by "+consumerName);
         return data;
      }
   }
    
   class Producer extends Thread{
       
      private DataBuffer buffer;
      private Random random;
       
      public Producer(DataBuffer buffer, String threadName){
         this.buffer = buffer;
         setName(threadName);
         random = new Random();
      }
       
      @Override
      public void run() {
         while(true){
            try {
               Thread.sleep(500);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            int data = random.nextInt(100);
            buffer.produce(data, getName());
         }
      }
   }
    
   class Consumer extends Thread{
       
      private DataBuffer buffer;
       
      public Consumer(DataBuffer buffer, String threadName){
         this.buffer = buffer;
         setName(threadName);
      }
       
      @Override
      public void run() {
         while(true){
            try {
               Thread.sleep(500);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            buffer.consume(getName());
         }
      }
   }
 
}
