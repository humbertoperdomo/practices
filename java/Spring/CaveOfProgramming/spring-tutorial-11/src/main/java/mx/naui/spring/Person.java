package mx.naui.spring;

import org.apache.log4j.Logger;

public class Person {
	private static final Logger logger = Logger.getLogger(Person.class);
	private int id;
	private String name;
	private int taxId;
	private Address address;

	public Person() {
	}

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void onCreate() {
		System.out.println("Person created: " + this);
	}

	public void onDestroy() {
		System.out.println("Person destroyed.");
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void speak() {
		if (logger.isDebugEnabled()) {
			logger.debug("Hello! I'm a person.");
		}
		System.out.println("Hello! I'm a person.");
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", taxId=" + taxId + ", address=" + address + "]";
	}
}
