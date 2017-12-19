package com.company.api.customer.model;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Customer {

	private String customerId;
	
	@NotNull(message = "first name can not be null.")
	@javax.validation.constraints.Size(min=1,message = "first name can not be empty.")
	@ApiModelProperty(required=true)
	private String firstname;
	
	@NotNull(message = "last name can not be null.")
	@javax.validation.constraints.Size(min=1,message = "last name can not be empty.")
	@ApiModelProperty(required=true)
	private String lastname;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes="yyyy-MM-dd",example="2001-12-30", required=true)
	@NotNull(message ="dob can not be null")
	private LocalDate dob;
	private Address address;
	
	@ApiModelProperty(notes="This is collection of key value pair.")
	private Map<String, String> customAttributes;
	
	public Customer() {
		super();
	}

	public Customer(String customerId, String firstname, String lastname, LocalDate dob, Address address,
			Map<String, String> customAttributes) {
		super();
		this.customerId = customerId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.customAttributes = customAttributes;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Map<String, String> getCustomAttributes() {
		return customAttributes;
	}

	public void setCustomAttributes(Map<String, String> customAttributes) {
		this.customAttributes = customAttributes;
	}
	
}
