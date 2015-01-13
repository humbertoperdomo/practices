// highArray.java
// demostrates array class with high-level interface
// to run this program: $ java HighArrayApp
////////////////////////////////////////////////////////////////////////////////
class Person
{
  private String lastName;
  private String firstName;
  private int age;
  //----------------------------------------------------------------------------
  public Person(String last, String first, int a)
  {                           // contructor
    lastName = last;
    firstName = first;
    age = a;
  }
  //----------------------------------------------------------------------------
  public void displayPerson()
  {
    System.out.print("  Last name: " + lastName);
    System.out.print(", First name: " + firstName);
    System.out.println(", Age: " + age);
  }
  //----------------------------------------------------------------------------
  public String getLast()     // get last name
  {
    return lastName;
  }
  //----------------------------------------------------------------------------
  public String getFirst()
  {
    return firstName;
  }
  //----------------------------------------------------------------------------
  public int getAge()
  {
    return age;
  }
}
////////////////////////////////////////////////////////////////////////////////
class DataArray
{
  private Person[] a;           // ref to array a
  private int nElems;         // number of data items
// -----------------------------------------------------------------------------
  public DataArray(int max)   // constructor
  {
    a = new Person[max];        // create the array
    nElems = 0;               // no items yet
  }
// -----------------------------------------------------------------------------
  public Person find(String searchKey) // find specific value
  {
    int j;
    for(j=0; j < nElems; j++) // for each element,
      if(a[j].getLast().equals(searchKey))   // found item?
        break;                // exit loop before end
    
    if(j == nElems)           // gone to end?
      return null;           // yes, can't find it
    else
      return a[j];            // no, found it
  }  // end find()
// -----------------------------------------------------------------------------
  // put person into array
  public void insert(String last, String first, int age)
  {
    a[nElems] = new Person(last, first, age);
    nElems++;                 // increment size
  }
// -----------------------------------------------------------------------------
  public boolean delete(String searchKey)
  {                           // delete person from array
    int j;
    for(j=0; j < nElems; j++) // look for it
      if(a[j].getLast().equals(searchKey))
        break;
    
    if(j == nElems)           // can't find it
      return false;
    else {                    // found it
      for(int k = j; k < nElems; k++) // shift down
         a[k] = a[k+1];
      nElems--;               // decrement size
      return true;
    }
  }  // end delete()
// -----------------------------------------------------------------------------
  public void displayA()       // display array content
  {
    for(int i = 0; i < nElems; i++) // for each element,
    {
      a[i].displayPerson(); // display it
    }
  }
// -----------------------------------------------------------------------------
} // end class HighArray
////////////////////////////////////////////////////////////////////////////////
class DataArrayApp
{
  public static void main(String[] args)
  {
    int maxSize = 100;        // array size
    DataArray arr;            // reference to array
    arr = new DataArray(maxSize); // create the array
    
    arr.insert("Perdomo", "Domingo",77);           // insert 10 items
    arr.insert("Nuno", "Jesus", 32);
    arr.insert("Cabrera", "Armida", 31);
    arr.insert("Alvarez", "Lourdes", 54);
    arr.insert("Hernandez", "Carmen", 78);
    arr.insert("Rodriguez", "Gloria", 55);
    arr.insert("Martinez", "Carlos", 34);
    arr.insert("Robles", "Ignacio", 53);
    arr.insert("Cholico", "Guadalupe", 52);
    arr.insert("Ortiz", "Juan", 30);
    
    arr.displayA();            // display items
    
    String searchKey = "Martinez";       // search for item
    Person found;
    found = arr.find(searchKey);
    if(found != null)
    {
      System.out.print("Found ");
      found.displayPerson();
    }
    else
      System.out.println("Can't find " + searchKey);

    System.out.println("Deleting Cholico, Rodriguez, and Cabrera");
    arr.delete("Cholico");           // delete 3 items
    arr.delete("Rodriguez");
    arr.delete("Cabrera");
    
    arr.displayA();            // display items again
  }  // end main()
}  // end class HighArrayApp
////////////////////////////////////////////////////////////////////////////////
