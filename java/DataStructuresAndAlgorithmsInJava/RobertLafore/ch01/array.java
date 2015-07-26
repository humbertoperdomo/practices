// array.java
// demonstrate Java arrays
// to run this program $ java arrayApp
////////////////////////////////////////////////////////////////////////////////
class ArrayApp {
  public static void main(String[] argv) {
    long[] arr;               // reference to array
    arr = new long[100];      // create array
    int nElems = 0;           // number of items

    int j;                    // loop counter
    long searchKey;           // key of item to search for
  //--------------------------------------------------------------------------
    arr[0] = 77;              // insert 10 items
    arr[1] = 99;
    arr[2] = 44;
    arr[3] = 55;
    arr[4] = 22;
    arr[5] = 88;
    arr[6] = 11;
    arr[7] = 00;
    arr[8] = 66;
    arr[9] = 33;
    nElems = 10;              // now 10 items in array
  //----------------------------------------------------------------------------
    for(j = 0; j < nElems; j++) // display items
      System.out.print(arr[j] + " ");
    System.out.println("");
  //----------------------------------------------------------------------------
    searchKey
  }
}
