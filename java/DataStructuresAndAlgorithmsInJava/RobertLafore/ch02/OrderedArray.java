// orderedArray.java
// demostrates ordered array class
// to run this program: $ java OrderedApp
////////////////////////////////////////////////////////////////////////////////
class OrderedArray
{
  private long[] a;           // ref to array a
  private int nElems;         // number of data items
  // ---------------------------------------------------------------------------
  public OrderedArray(int max)
  {
    a = new long[max];
    nElems = 0;
  }
  // ---------------------------------------------------------------------------
  public int size()
  {
    return nElems;
  }
  // ---------------------------------------------------------------------------
  public int find(long searchKey)
  {
    int lowerBound = 0;
    int upperBound = nElems - 1;
    int curIn;

    while(true)
    {
      curIn = (lowerBound + upperBound) / 2;
      if(a[curIn] == searchKey)
        return curIn;         // found it
      else if(lowerBound > upperBound)
        return nElems;        // can't find it
      else                    // divide range
      {
        if(a[curIn] < searchKey)
          lowerBound = curIn + 1; // it's in upper half
        else
          upperBound = curIn - 1; // it's in lower half
      }  // end else divide range
    }  // end while
  } // end find()
  // ---------------------------------------------------------------------------
  public int findInsert(long searchKey)  
  {
    int lowerBound = 0;
    int upperBound = nElems;
    int curIn;
    while(lowerBound != upperBound)
    {
      curIn = (lowerBound + upperBound) / 2;
      if(a[curIn] <= searchKey)
        /* This index, and everything below it, must not be the first element
         * greater than what we're looking for because this element is no greater
         * than the element.
         */
        lowerBound = curIn + 1;
      else
        /* This element is at least as large as the element, so anything after it can't
         * be the first element that's at least as large.
         */
        upperBound = curIn; 
    }  // end while
    return lowerBound;
  } // end findInsert()
  // ---------------------------------------------------------------------------
  public void insert(long value)
  {
    int j;
    j = findInsert(value);
    for(int k = nElems; k > j; k--) // move bigger ones up
      a[k] = a[k-1];
    a[j] = value;             // insert it
    nElems++;                 // increment size
  }
  // ---------------------------------------------------------------------------
  public boolean delete(long value)
  {
    int j = find(value);
    if(j == nElems)           // can't find it
      return false;
    else                      // found it
    {
      shiftDown(j);
      return true;
    }
  }  // end delete()
  // ---------------------------------------------------------------------------
  public void shiftDown(int index)
  {
    for(int k = index; k < nElems; k++) // move bigger ones down
      a[k] = a[k+1];
    nElems--;               // decrement size
  }
  // ---------------------------------------------------------------------------
  public void display()       // display items
  {
    for(int i=0; i < nElems; i++) // for each element
      System.out.print(a[i] + " ");
    System.out.println("");
  }
  // ---------------------------------------------------------------------------
  public OrderedArray merge(OrderedArray b)
  {
    OrderedArray c = new OrderedArray(b.size() + this.size() + 1);
    
    for(int i = 0; i < this.size(); i++)
      c.insert(this.a[i]);

    for(int j = 0; j < b.size(); j++)
      c.insert(b.a[j]);

    return c;
  }
  // ---------------------------------------------------------------------------
  public OrderedArray noDups()
  {
    long currentKey = a[0];
    for(int i = 1; i < nElems; i++)
    {
      if(currentKey == a[i])
      {  
        shiftDown(i);
        --i;
      }
      else
        currentKey = a[i];
    }

    return this;
  }
  // ---------------------------------------------------------------------------
}  // end class OrderedArray
////////////////////////////////////////////////////////////////////////////////
class OrderedApp
{
  public static void main(String[] args)
  {
    int maxSize = 100;        // array size
    OrderedArray arr, arrB, dest;         // reference to array
    arr = new OrderedArray(maxSize); // create the array
    arrB = new OrderedArray(maxSize);
    
    arr.insert(77);           // insert 10 items
    arr.insert(44);
    arr.insert(99);
    arr.insert(44);
    arr.insert(00);
    arr.insert(55);
    arr.insert(22);
    arr.insert(88);
    arr.insert(11);
    arr.insert(66);
    arrB.insert(33);
    arrB.insert(55);
    arrB.insert(56);
    arrB.insert(56);
    arrB.insert(55);
    arrB.insert(54);
    arrB.insert(55);
    arrB.insert(100);
    arrB.insert(10);
    arrB.insert(0);

    int searchKey = 55;       // serach for item
    if(arr.find(searchKey) != arr.size())
      System.out.println("Found: "+ searchKey);
    else
      System.out.println("Can't find " + searchKey);

    arr.display();            // display items

    arr.delete(00);           // delete 3 itmes
    arr.delete(55);
    arr.delete(99);

    arr.display();            // display items again
    arrB.display();
    arr.merge(arrB).noDups().display();

  }  // end main()
}  // end class OrdewredApp
////////////////////////////////////////////////////////////////////////////////
