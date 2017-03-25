package mx.naui.spring;

import org.apache.log4j.Logger;

public class Person {
	private static final Logger logger = Logger.getLogger(Person.class);
	private int id;
	private String name;

	public Person() {
	}

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void speak() {
		if (logger.isDebugEnabled()) {
			logger.debug("Hello! I'm a person.");
		}
		System.out.println("Hello! I'm a person.");
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
}
