// highArray.java
// demostrates array class with high-level interface
// to run this program: $ java HighArrayApp
////////////////////////////////////////////////////////////////////////////////
class HighArray
{
  private long[] a;           // ref to array a
  private int nElems;         // number of data items
  // -----------------------------------------------------------------------------
  public HighArray(int max)   // constructor
  {
    a = new long[max];        // create the array
    nElems = 0;               // no items yet
  }
  // -----------------------------------------------------------------------------
  public boolean find(long searchKey) // find specific value
  {
    int j;
    for(j=0; j < nElems; j++) // for each element,
      if(a[j] == searchKey)   // found item?
        break;
    
    if(j == nElems)
      return false;
    else
      return true; 
  }  // end find()
  // -----------------------------------------------------------------------------
  public long getMax()
  {
    if(nElems > 0)
    {
      long max = 0;
      for(int i = 0; i < nElems; i++)
        if(a[i] > max)
          max = a[i];
      return max;
    }
    else
      return -1;
  }
  // -----------------------------------------------------------------------------
  public long removeMax()
  {
    if(nElems > 0)
    {
      long max = 0;
      int indexMax = 0;
      for(int i = 0; i < nElems; i++)
        if(a[i] > max)
        {
          max = a[i];
          indexMax = i;
        }
      shiftDown(indexMax);
      return max;
    }
    else
      return -1;
  }
  // -----------------------------------------------------------------------------
  public void shiftDown(int index)
  {
    for(int k = index; k < nElems; k++)
      a[k] = a[k+1];
    nElems--;
  }
  // -----------------------------------------------------------------------------
  public void insert(long value) // put element into array
  {
    a[nElems] = value;        // insert it
    nElems++;                 // increment size
  }
  // -----------------------------------------------------------------------------
  public boolean delete(long searchKey)
  {
    int j;
    for(j=0; j < nElems; j++) // loop for it
      if(a[j] == searchKey)
        break;
    
    if(j == nElems)           // can't find it
      return false;
    else {
      shiftDown(j);
      return true;
    }
  }  // end delete()
  // -----------------------------------------------------------------------------
  public void display()       // display array content
  {
    for(int i = 0; i < nElems; i++) // for each element,
      System.out.print(a[i] + " "); // display it
    System.out.println("");
  }
  // -----------------------------------------------------------------------------
} // end class HighArray
////////////////////////////////////////////////////////////////////////////////
class HighArrayApp
{
  public static void main(String[] args)
  {
    int maxSize = 100;        // array size
    HighArray arr, sorted;            // reference to array
    arr = new HighArray(maxSize); // create the array
    sorted = new HighArray(maxSize);
    
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
    /*
    int searchKey = 35;       // search for item
    if(arr.find(searchKey))
      System.out.println("Found " + searchKey);
    else
      System.out.println("Can't find " + searchKey);

    System.out.println("The max value is: " + arr.getMax());
    
    arr.delete(00);           // delete 3 items
    arr.delete(55);
    arr.delete(99);
    */

    // implement sorting using removeMax
    for(int i = 0; i < 10; i++)
      sorted.insert(arr.removeMax());
    
    arr.display();            // display items again
    sorted.display();         // display sorted items
  }  // end main()
}  // end class HighArrayApp
////////////////////////////////////////////////////////////////////////////////
