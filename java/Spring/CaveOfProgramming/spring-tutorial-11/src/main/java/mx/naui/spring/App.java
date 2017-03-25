package mx.naui.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        Person person = (Person) context.getBean("person");
        Person anotherPerson = (Person) context.getBean("person");

        person.setTaxId(666);

        System.out.println(anotherPerson);

        ((ClassPathXmlApplicationContext)context).close();
    }
}
