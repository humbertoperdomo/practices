package mx.naui.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

    	FruitBasket basket = (FruitBasket) context.getBean("basket");

        System.out.println(basket);

        ((ClassPathXmlApplicationContext)context).close();
    }
}
