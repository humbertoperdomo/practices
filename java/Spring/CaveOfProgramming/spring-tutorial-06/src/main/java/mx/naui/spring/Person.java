package mx.naui.spring;

import org.apache.log4j.Logger;

public class Person {
	private static final Logger logger = Logger.getLogger(Person.class);

	public void speak() {
		if (logger.isDebugEnabled()) {
			logger.debug("Hello! I'm a person.");
		}
		System.out.println("Hello! I'm a person.");
	}
}
