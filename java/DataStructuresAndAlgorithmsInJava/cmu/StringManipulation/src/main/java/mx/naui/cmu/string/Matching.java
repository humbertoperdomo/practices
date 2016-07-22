/*************************************************************************
 *   Demonstrates Pattern Matching in Java
 *
 *************************************************************************/
package mx.naui.cmu.string;

import java.util.regex.*;
import java.util.*;

public class Matching
{
   public static void main(String[] args)
   {
		//matching
		String seq = "CCCAA";
		Pattern p = Pattern.compile("CCCA+"); //"C*A*" //"C*A+"
		Matcher m = p.matcher(seq);
		boolean res = m.matches();
		System.out.println("matching CCC " + res);
      System.out.println();

      //count the number of ACC?
		seq = "CGTATCCCACAGCACCACACCCAACAACCCA";
      p = Pattern.compile("ACC");  //"A{1}C{2}"
      m = p.matcher(seq);
      int count = 0;
      while( m.find() ) count++;
      System.out.println("there are " + count + " ACC");
      System.out.println();

      //count the number of pairs: A followed by C or G or T?
		seq = "CGTATCCCACAGCACCACATCCAACAACCCA";
      p = Pattern.compile("A(C|G|T)");
      m = p.matcher(seq);
      count = 0;
      while( m.find() ) count++;
		System.out.println("there are " + count + " A* pairs");
      System.out.println();

		//find the longest run of the same nucleotide
		seq = "CGTATCCCACAGCACCAACATTTTTTCCAACAACCCCA";
		p = Pattern.compile("A+|C+|G+|T+");
      m = p.matcher(seq);
      int max = 0;
      while( m.find() )
      	if(m.group().length() > max )
      		max = m.group().length();
      System.out.println("max = " + max);
      System.out.println();

      //print found matches for ACA, ACCA, ACCCA, and so on
      p = Pattern.compile("AC+A");
      m = p.matcher(seq);
		System.out.println("here are the matches ");
      while( m.find() )	System.out.println("\t" + m.group());
      System.out.println();

      //replace all ACC with _?
		seq = "CGTATCCCACAGCACCAACATTTTTTCCAACAACCCCA";
      p = Pattern.compile("AC+A");
      m = p.matcher(seq);
      System.out.println("replacing all AC+C with _");
      System.out.println(m.replaceAll("_"));
      System.out.println();

      //count consonants
      String word = "code example";
      p = Pattern.compile("[^aeiou ]");
      m = p.matcher(word);
      count = 0;
      while( m.find() ) count++;
      System.out.println("there are " + count + " consonants in \"" + word+"\"");
      System.out.println();
   }
}
