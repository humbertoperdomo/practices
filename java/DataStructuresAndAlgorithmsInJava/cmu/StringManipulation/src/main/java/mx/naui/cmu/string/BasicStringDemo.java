package mx.naui.cmu.string;

public class BasicStringDemo
{
	public static void main(String[] args)
	{
		System.out.println("Google".charAt(5));            //e
		System.out.println("Yahoo".substring(2));          //hoo
		System.out.println("Alibaba".substring(1,3));      //li
		System.out.println("Microsoft".indexOf("so"));     //5
		System.out.println("mississippi".indexOf("is",2)); //4
		System.out.println("Alibaba".startsWith("ba"));    //false
		System.out.println("Alibaba".startsWith("ba",3));  //true
		System.out.println("a".compareTo("c"));            //-2
		System.out.println("a".compareTo("A"));            //32
	}
}
