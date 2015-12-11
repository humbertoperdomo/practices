package mx.naui.thread;

import java.io.*;

public class CircularBufferTest {
   // in: reader for reading input data
   private static Reader in = 
         new InputStreamReader(System.in);

   private static char getNextChar() {
      char ch = ' ';
      try {
         ch = (char)in.read();
      }
      catch (Exception exception) {
         System.err.println("Read Error");
         ch = ' ';
      }
      return ch;
   }

   public static void main(String[] args) {
      CircularBuffer circularBuffer = new CircularBuffer(10); // 10 chars
      char ch;
      while ((ch = getNextChar()) != 'q') {
         switch (ch) {
         case 'i':
            ch = getNextChar();
            circularBuffer.doPut(ch, Thread.currentThread().getName());
            System.out.println(ch + " inserted");
            break;
         case 'd':
            System.out.println(circularBuffer.doGet(Thread.currentThread().getName()) + 
               " deleted");
            break;
         case 'p':
            circularBuffer.printq();
            break;
         case 'f':
            System.out.println("Front element: " + circularBuffer.front());
            break;
         default: System.out.println("Input error");
         }
         while ((ch = getNextChar()) != '\n')
            ;
      }
   }
}
