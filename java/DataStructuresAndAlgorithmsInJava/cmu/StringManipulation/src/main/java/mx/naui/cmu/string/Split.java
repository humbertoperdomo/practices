/*************************************************************************
 *   Demonstrates using the split() method
 *
 *************************************************************************/
package mx.naui.cmu.string;

import java.util.*;

public class Split
{
	public static void main (String[] args )
	{
		String str = "On_the_edge_of_history";
		String[] tmp = str.split("_");
		System.out.println( Arrays.toString(tmp));
		System.out.println( );

		str = "On_the___edge_of____history";
		tmp = str.split("_+");
		System.out.println( Arrays.toString(tmp));
		System.out.println( );

		str = "On***the___edge**of____history";
		tmp = str.split("_+|\\*+");
		System.out.println( Arrays.toString(tmp));
		System.out.println( );
	}
}

