package mx.naui.agilowen; 

/**
 * Addition
 *
 */
public class Addition 
{
  private int[] addend1;
  private int[] addend2;
  private int carry;
  private int[] result;
  private int len;
  
  public Addition(int[] addend1, int[] addend2) {
    this.addend1 = addend1;
    this.addend2 = addend2;
    len = Integer.valueOf(addend1.length).compareTo(addend2.length);
    result = new int[(len >= 0 ? addend1.length : addend2.length) + 1];
    carry = 0;
  }

  public int[] getAddend1() {
    return addend1;
  }

  public int[] getAddend2() {
    return addend2;
  }

  public int[] getResult() {
    return result;
  }

  public void add() {
    int i;
    int ll = len <= 0 ? addend1.length : addend2.length;

    for (i = 0; i < ll; i++) {
      result[i] = (addend1[i] + addend2[i] + carry) % 10;
      carry = (addend1[i] + addend2[i] + carry) / 10;
    }

    if (len > 0) { 
      for (i = i; i < addend1.length; i++) {
        result[i] = (addend1[i] + carry) % 10;
        carry = (addend1[i] + carry) / 10;
      }
    } else if (len < 0) {
      for (i = i; i < addend2.length; i++) {
        result[i] = (addend2[i] + carry) % 10;
        carry = (addend2[i] + carry) / 10;
      }
    }

    result[i] = carry;
  } 
  public static void printArrayIntBackward(int[] array) {
    for (int i = array.length - 1; i >= 0; i--) {
      System.out.print(array[i]);
    }
  }

  public static int[] stringToArray(String number) {
    int[] tmp = null; 

    if (number != null && number.length() > 0) {
      tmp = new int[number.length()];
      for (int i = number.length() - 1, j = 0; i >= 0; i--, j++) {
        tmp[j] = Character.getNumericValue(number.charAt(i));
      }
    }
    
    return tmp;
  }

  public static void main( String[] args )
  {
    if (args.length < 2) {
      System.out.println("Usage: Addition ADDEND_ONE ADDEND_TWO");
      System.exit(1);
    }

    Addition a = new Addition(Addition.stringToArray(args[0]), Addition.stringToArray(args[1]));
    a.add();
    Addition.printArrayIntBackward(a.getAddend1());
    System.out.print(" + ");
    Addition.printArrayIntBackward(a.getAddend2());
    System.out.print(" = ");
    Addition.printArrayIntBackward(a.getResult());
    System.out.println("");
  }
}
