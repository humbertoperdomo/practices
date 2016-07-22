package mx.naui.derekbanas.stacksandqueues;

import java.util.Arrays;

public class TheStack {
  private String[] stackArray;
  private int stackSize;
  private int topOfStack = -1;

  public TheStack(int size) {
    stackSize = size;
    stackArray = new String[size];
    Arrays.fill(stackArray, "-1");
  }

  public void displayTheStack() {
    int dashes = (stackSize * 5) + 1;
    for (int n = 0; n < dashes; n++)
      System.out.print("—");

    System.out.println();

    for (int n = 0; n < stackSize; n++)
      System.out.print("|  " + n + " ");
    System.out.println("|");

    for (int n = 0; n < dashes; n++)
      System.out.print("—");
    System.out.println();

    for (int n = 0; n < stackSize; n++)
      System.out.print("| " + (stackArray[n].equals("-1") ? "  " : stackArray[n]) + " ");
    System.out.println("|");

    for (int n = 0; n < dashes; n++)
      System.out.print("—");
    System.out.println();
  }

  public void push(String input) {
    if(topOfStack + 1 < stackSize) {
      stackArray[++topOfStack] = input;
    } else {
      System.out.println("Sorry but the stack is full");
    }

    displayTheStack();

    System.out.println("PUSH " + input + " was added to the stack\n");
  }

  public String pop() {
    if(topOfStack >= 0) {
      String removed = stackArray[topOfStack];
      stackArray[topOfStack] = "-1";
      topOfStack--;
      displayTheStack();
      System.out.println("POP " + removed + " was remove from the stack\n");
      return removed;
    } else {
      displayTheStack();
      System.out.println("Sorry but the stack is empty");
      return "-1";
    }
  }

  public String peek() {
    displayTheStack();
    System.out.println("PEEK " + stackArray[topOfStack] + " is at the top of the stack\n");
    return stackArray[topOfStack];
  }

  public void pushMany(String multipleValues) {
    String[] tempString = multipleValues.split(" ");
    for (int i = 0; i < tempString.length; i++)
      push(tempString[i]);
  }

  public void popAll() {
    for (int i = topOfStack; i >= 0; i--)
      pop();
  }

  public static void main(String[] args) {
    TheStack theStack = new TheStack(11);
    theStack.push("10");
    theStack.push("15");
    theStack.peek();
    theStack.pop();
    theStack.pushMany("12 13 14 15");
    theStack.popAll();

  }
}
