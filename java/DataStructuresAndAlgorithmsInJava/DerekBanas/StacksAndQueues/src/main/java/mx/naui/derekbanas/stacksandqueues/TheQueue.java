package mx.naui.derekbanas.stacksandqueues;

import java.util.Arrays;

public class TheQueue {
  private String[] queueArray;
  private int queueSize;
  private int front, rear, numberOfItems = 0;
  
  public TheQueue(int size) {
    queueSize = size;
    queueArray = new String[size];
    Arrays.fill(queueArray, "-1");
  }
  
  public void displayTheQueue() {
    int dashes = (queueSize * 6) + 1;
    for (int n = 0; n < dashes; n++)
      System.out.print("—");

    System.out.println();

    for (int n = 0; n < queueSize; n++)
      System.out.print("|  " + n + "  ");
    System.out.println("|");

    for (int n = 0; n < dashes; n++)
      System.out.print("—");
    System.out.println();

    for (int n = 0; n < queueSize; n++)
      System.out.print("| " + (queueArray[n].equals("-1") ? "  " : queueArray[n]) + "  ");
    System.out.println("|");

    for (int n = 0; n < dashes; n++)
      System.out.print("—");
    System.out.println();
    
    for (int n = 0; n < queueSize; n++) {
      if (front == rear && !queueArray[n].equals("-1") && (n == front)) 
        System.out.print("   F R ");
      else if (n == front)
        System.out.print("   F  ");
      else if (n == rear)
        System.out.print("   R  ");
      else
        System.out.print("     ");
    }
    System.out.println();
  }

  public void insert(String input) {
    if (numberOfItems + 1 <= queueSize) {
      queueArray[numberOfItems == 0 ? rear : ++rear] = input;
      numberOfItems++;
      System.out.println("INSERT " + input + " was added to the queue\n");
    } else {
      System.out.println("Sorry but the queue is full");
    }
  }

  public void remove() {
    if (numberOfItems > 0) {
      System.out.println("REMOVE " + queueArray[front] + " was removed from the queue\n");
      queueArray[front++] = "-1";
      numberOfItems--;
    } else {
      System.out.println("Sorry but the queue is empty");
    }
  }

  public void peek() {
    System.out.println("The first item of the queue is " + queueArray[front] + "\n");
  }

  public void priorityInsert(String input) {
    int i;

    if (numberOfItems == 0) {
      insert(input);
    } else {
      for (i = numberOfItems - 1; i >= 0; i--) 
        if (Integer.parseInt(input) > Integer.parseInt(queueArray[i]))
          queueArray[i + 1] = queueArray[i];
        else 
          break;
      queueArray[i + 1] = input;
      rear++;
      numberOfItems++;
    }
  }

  public static void main(String[] args) {
    TheQueue theQueue = new TheQueue(10);
    theQueue.priorityInsert("10");
    theQueue.priorityInsert("19");
    theQueue.priorityInsert("15");
    theQueue.priorityInsert("11");
    theQueue.displayTheQueue();
    theQueue.remove();
    theQueue.displayTheQueue();
    theQueue.peek();
    theQueue.remove();
    theQueue.displayTheQueue();
    theQueue.remove();
    theQueue.displayTheQueue();
  }
}
