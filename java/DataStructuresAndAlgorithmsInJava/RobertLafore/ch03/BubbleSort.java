// BubbleSort.java
// demostrates bubble sort
// to run this program: $ java BubbleSortApp
////////////////////////////////////////////////////////////////////////////////
class ArrayBub
{
  private long[] a;           // ref to array a
  private int nElems;         // number of data items
//------------------------------------------------------------------------------
  public ArrayBub(int max)    // constructor
  {
    a = new long[max];        // create the array
    nElems = 0;               // no items yet
  }
//------------------------------------------------------------------------------
  public void insert(long value) // put element into array
  {
    a[nElems++] = value;        // insert it
    //nElems++;                 // increment size
  }
//------------------------------------------------------------------------------
  public void display()       // display array contents
  {
    for(int i = 0; i < nElems; i++) // for each element
      System.out.print(a[i] + " "); // display it
    System.out.println("");
  }
//------------------------------------------------------------------------------
  public void bubbleSort() {
    int out, in, outReverse;
    long lStartTime = System.nanoTime();

    for(out = nElems - 1, outReverse =  0; out > outReverse; out--, outReverse++) {// outer loop (backward)
      for(in=outReverse; in < out; in++) // inner loop (forward)
        if(a[in] > a[in+1])   // out of order?
          swap(in, in+1);     // swap them
      for(in = out - 1 ; in > outReverse; in--) // inner loop (forward)
        if(a[in] < a[in-1])   // out of order?
          swap(in, in-1);     // swap them
    }
    
    System.out.println("Time: " + (double)(System.nanoTime() - lStartTime)/1000000.00);
  } // end bubbleSort()

//------------------------------------------------------------------------------
  public void oddeEvenSort() {
    int out, in;
    long lStartTime = System.nanoTime();
    int oddEven = nElems % 2;


    for(int i = 0; i < (nElems - 1 - oddEven); ) {

    }

    for(out = nElems - 1, outReverse =  0; out > outReverse; out--, outReverse++) {// outer loop (backward)
      for(in = outReverse; in < out; (in++)++) // inner loop (forward)
        if(a[in] > a[in + 2])   // out of order?
          swap(in, in + 2);     // swap them
      for(in = out - 2 ; in > outReverse; (in--)--) // inner loop (forward)
        if(a[in] < a[in - 2])   // out of order?
          swap(in, in - 2);     // swap them
    }

    System.out.println("Time: " + (double)(System.nanoTime() - lStartTime)/1000000.00);
  }
//------------------------------------------------------------------------------
  public void swap(int one, int two)
  {
    long temp = a[one];
    a[one] = a[two];
    a[two] = temp;
  }
} // end class ArrayBub
////////////////////////////////////////////////////////////////////////////////
class BubbleSortApp
{
  public static void main(String[] args) {
    int maxSize = 0;
    StringBuilder message = new StringBuilder("");
    boolean parameterSize = false;
    boolean inverse = false;
    boolean sorted = false;
    for (int i = 0; i < args.length; i++){
      if (args[i].equals("?")) {
        printUsage("");
        System.exit(1);
      }

      if (args[i].equals("-s")) {
        parameterSize = true;
        if (++i < args.length) {
          try {
            maxSize = Integer.parseInt(args[i]);        // array size
          } catch (NumberFormatException nfe) {
            message.append("ARRAYSIZE must to be numeric");
          }
        }
      }

      if (args[i].equals("-i")) {
        inverse = true;
      }

      if (args[i].equals("-o")) {
        sorted = true;
      }
    }

    if ((maxSize <= 0)) {
      if (parameterSize && message.length() == 0){
        message.append("ARRAYSIZE must to be greater than 0");
      }
      printUsage(message.toString());
      System.exit(1);
    }

    if (inverse && sorted){
      message.append("Cannot both sorted and inverse entries");
      printUsage(message.toString());
      System.exit(1);
    }

    ArrayBub arr;             // reference to array
    arr = new ArrayBub(maxSize); // create the array
    
    if (inverse) {
      int max = maxSize;
      for (int j = 0; j < maxSize; j++, max--) {
        arr.insert((long) max);  
      }
    } else if (sorted) {
      for (int j = 0; j < maxSize; j++) {
        arr.insert((long) (j + 1));
      }
    } else {
      for (int j = 0; j < maxSize; j++) {
        long n = (long) (Math.random()*(maxSize-1));
         arr.insert(n);
      }
    }
    
    //arr.display();            // display items

    arr.bubbleSort();         // bubble sort them

    //arr.display();            // display items again
  } // end main()

  private static void printUsage(String message) {
    System.out.println("Usage: BubbleSortApp -s ARRAYSIZE [-i] [-o]");
    System.out.println(message);
  }
} // end class BubbleSortApp

