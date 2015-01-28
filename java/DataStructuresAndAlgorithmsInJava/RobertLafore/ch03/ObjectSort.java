// ObjectSort.java
// demostrates sorting objects (uses insertion sort)
// to run this program: $ java ObjectSortApp
////////////////////////////////////////////////////////////////////////////////
class Person {
  private String lastName;
  private String firstName;
  private int age;
  //----------------------------------------------------------------------------
  public Person(String last, String first, int a) { // constructor
    lastName = last;
    firstName = first;
    age = a;
  }
  //----------------------------------------------------------------------------
  public void displayPerson() {
    System.out.print(" Last name: " + lastName);
    System.out.print(", First name: " + firstName);
    System.out.println(" , Age: " + age);
  }
  //----------------------------------------------------------------------------
  public String getLast() {   // get last name
    return lastName;
  }
} // end class Person
////////////////////////////////////////////////////////////////////////////////
class ArrayInOb {
  private Person[] a;         // ref to array a
  private int nElems;             // number of data items
  //----------------------------------------------------------------------------
  public ArrayInOb(int max) { // constructor
    a = new Person[max];      // create the array
    nElems = 0;               // no items yet
  }
  //----------------------------------------------------------------------------
  public void insert(String last, String first, int age) {
    a[nElems++] = new Person(last, first, age);
  }
  //----------------------------------------------------------------------------
  public void display() {     // displays array contents
    for(int i = 0; i < nElems; i++) // for each element
      a[i].displayPerson();   // display it
    System.out.println("");
  }
  //----------------------------------------------------------------------------
  public void insertionSort() {
    int in, out;

    for( out = 1; out < nElems; out++) {  // out is dividing line
      Person temp = a[out];   // remove marked person
      in = out;               // start shifting at out

      while(in > 0 && a[in - 1].getLast().compareTo(temp.getLast()) > 0) {  // until smaller found
        a[in] = a[in -1];     // Shift to the right
        --in;                 // go left one position
      }  
      a[in] = temp;           // insert marked item
    } // end for
  } // end insertionSort()
} // end class ArrayInOb
////////////////////////////////////////////////////////////////////////////////
class ObjectSortApp {
  public static void main(String[] argv) {
    int maxSize = 100;        // array size
    ArrayInOb arr;            // reference to array
    arr = new ArrayInOb(maxSize); // create the array

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

    System.out.println("Before sorting: ");
    arr.display();

    arr.insertionSort();

    System.out.println("After sorting: ");
    arr.display();
  } // end main()
} // end class ObjectSortApp
