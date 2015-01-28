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
}
////////////////////////////////////////////////////////////////////////////////
class InsertionSortApp {
  public static void main(String[] args) {
    int maxSize = 100;        // array size
    ArrayIns arr;             // reference to array
    arr = new ArrayIns(maxSize); // create the array

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

    arr.insertionSort();         // bubble sort them

    arr.display();            // display items again
  } // end main()
} // end class InsertionSortApp
