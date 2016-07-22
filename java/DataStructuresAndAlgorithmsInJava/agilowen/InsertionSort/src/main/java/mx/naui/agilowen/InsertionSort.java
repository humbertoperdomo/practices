package mx.naui.agilowen;

/**
 * Insertion sort
 *
 */
public class InsertionSort 
{
  private int[] array;
  
  public InsertionSort() {
  }

  public InsertionSort(int[] array) {
    this.array = array;
  }

  public void sort() {
    int in, out;
    
    for (out = 1; out < array.length; out++) {
      int temp = array[out];
      in = out;
      while (in > 0 && array[in -1] >= temp) {
        array[in] = array[in -1];
        --in;
      }
      array[in] = temp;
    }
  }

  public void display() {
    for (int i = 0; i < array.length; i++)
      System.out.print(array[i] + " ");
    System.out.println("");
  }

  public static void main( String[] args )
  {
    int size = 10;
    int max = size;
    int[] array = new int[size];
    if (args.length > 0) {
      if (args[0].equals("-i")) {
        for (int i = 0; i < size; i++, max--) {
          array[i] = max;
        }
      } else if (args[0].equals("-o")) {
        for (int i = 0; i < size; i++) {
          array[i] = i + 1;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        int n = (int) (Math.random()*(size - 1));
        array[i] = n;
      }
    }
    InsertionSort is = new InsertionSort(array);
    is.display();
    is.sort();
    is.display();
  }
}
