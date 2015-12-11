package mx.naui.thread;

// CircularBuffer.java: queue implementation
public class CircularBuffer {
  private char dataBuffer[];
  private int capacity;
   
  private int front = 0;
  private int currentSize = 0;
   
  public CircularBuffer(int capacity) {
    this.capacity = capacity;
    dataBuffer = new char[capacity];
  }
   
  private boolean isEmpty(){
    return (currentSize == 0);
  }
   
  private boolean isFull(){
    return (currentSize == capacity);
  }
   
  public synchronized void  doPut(char data, String producerName){
    while(isFull()){
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    dataBuffer[(front+currentSize)%capacity] = data;
    currentSize++;
    System.out.println("Data "+ data +" produced by "+producerName);
    notifyAll();
  }
   
  public synchronized char doGet(String consumerName){
    while(isEmpty()){
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    char data = dataBuffer[front];
    front = (front+1)%capacity;
    currentSize--;
    notifyAll();
    System.out.println("Data "+ data+" consumed by "+consumerName);
    return data;
  }

  public char front() {
    return (isEmpty() ? dataBuffer[front] : '\0' );
  }
   
  public synchronized void printq() {
    System.out.print("Size: " + currentSize +
        ", rp: " + ((front+currentSize)%capacity) + ", fp: " + front + ", buffer: ");
    for (int i = 0; i < capacity; i++)
      System.out.print("buffer[" + i + "]=" 
          + dataBuffer[i] + "; ");
    System.out.println();
  }
}
