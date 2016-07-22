/*************************************************************************
 *   Demonstrates time comparisons between two ways of string creation:

 *       String s1 = new String("Fester");
 *
 *	    String s3 = "Fester";
 *
 *************************************************************************/
package mx.naui.cmu.string;

public class StringOptimization
{
	public static void main (String[] args )
	{
		int size = 200000;
		long t1, t2;

		t1 = System.currentTimeMillis();
		test1(size);
		t2 = System.currentTimeMillis();
		System.out.println(" Computing time: " + (t2-t1) + " millisec");


		t1 = System.currentTimeMillis();
		test2(size);
		t2 = System.currentTimeMillis();
		System.out.println(" Computing time: " + (t2-t1) + " millisec");
	}

	public static void test1(int size)
	{
		String[] str = new String[size];

		for(int k = 0; k < size; k++)
			str[k] = new String("a");
	}

	public static void test2(int size)
	{
		String[] str = new String[size];

		for(int k = 0; k < size; k++)
			str[k] = "a";
	}
}

