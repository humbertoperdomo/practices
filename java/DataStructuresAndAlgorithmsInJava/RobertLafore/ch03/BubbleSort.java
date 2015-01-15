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
  public void bubbleSort()
  {
    int out, in;
    long lStartTime = System.nanoTime();

    for(out = nElems - 1; out > 1; out--) // outer loop (backward)
      for(in=0; in < out; in++) // inner loop (forward)
        if(a[in] > a[in+1])   // out of order?
          swap(in, in+1);     // swap them
    
    System.out.println("Time: " + (double)(System.nanoTime() - lStartTime)/1000000.00);
  } // end bubbleSort()
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
  public static void main(String[] args)
  {
    int maxSize = 100;        // array size
    ArrayBub arr;             // reference to array
    arr = new ArrayBub(maxSize); // create the array

    arr.insert(77);           // insert 10 items
    arr.insert(99);
    arr.insert(44);
    arr.insert(55);
    arr.insert(22);
    arr.insert(88);
    arr.insert(11);
    arr.insert(00);
    arr.insert(66);
    arr.insert(33);
    
    arr.display();            // display items

    arr.bubbleSort();         // bubble sort them

    arr.display();            // display items again
  } // end main()
} // end class BubbleSortApp