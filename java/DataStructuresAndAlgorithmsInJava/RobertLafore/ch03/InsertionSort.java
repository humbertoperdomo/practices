// InsertionSort.java
// demostrates insertion sort
// to run this program: $ java InsertionSortApp
////////////////////////////////////////////////////////////////////////////////
class ArrayIns
{
  private long[] a;           // ref to array a
  private int nElems;         // number of data items
//------------------------------------------------------------------------------
  public ArrayIns(int max)    // constructor
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
  public void insertionSort()
  {
    int out, in, min;
    long lStartTime = System.nanoTime();
    
    for(out = 1; out < nElems; out++) { // out is dividing the line
      long temp = a[out];     // remove marked item
      in = out;               // start shifts at out
      while(in > 0 && a[in -1] >= temp) { // until one is smaller
        a[in] = a[in - 1];    // shift item to right
        --in;                 // go left one position
      }
      a[in] = temp;           // insert marked item
    } // end for
    System.out.println("Time: " + (double)(System.nanoTime() - lStartTime)/1000000.00);
  } // end insertionSort()

  public long median() {
    insertionSort();
    
    return a[nElems/2];

  }

  public void noDups() {
    int prev = 0;
    int curr = 1; 
    int next = 1;
    int size = nElems;
    while (next < size){ 
      if (a[curr] == a[prev]) {
        if (++next < size) { // exist one more element
          a[curr] = a[next]; // set to the curr position the next element
          a[next] = -1; // set a flag to the moved element
        }
        nElems--;
      } else {
        prev++;
        curr++;
        if (a[curr] == -1) { // current position has a flag of a moved element
          if (++next < size) { // exist one more element
            a[curr] = a[next]; // set to the curr position the next element
            a[next] = -1; // set a flag to the moved element
          }
        } else {
          next++;
        }
      }
    }
  }
}

////////////////////////////////////////////////////////////////////////////////
class InsertionSortApp {
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

    if (maxSize <= 0) {
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

    ArrayIns arr;             // reference to array
    arr = new ArrayIns(maxSize); // create the array

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
    
    arr.display();            // display items

    arr.insertionSort();         // bubble sort them
    //arr.display();            // display items
    //System.out.println("Median value is: " + arr.median());
    arr.noDups();
    arr.display();            // display items again
  } // end main()

  private static void printUsage(String message) {
    System.out.println("Usage: InsertionSortApp -s ARRAYSIZE [-i] [-o]");
    System.out.println(message);
  }
} // end class InsertionSortApp
