package com.company.api.customer.model;

public class Address {

	private String home;
	private String office;
	private String email;

	
	public Address() {
		super();
	}

	public Address(String home, String office, String email) {
		super();
		this.home = home;
		this.office = office;
		this.email = email;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
