package com.company.api.customer.model;

/**
 * 
 * @author Bhavin Mehta
 *
 */
public class CustomerResponse {

	private String customerId;
	private String errors;
	private boolean success;
	
	

	public CustomerResponse(String customerId, String errors, boolean success) {
		super();
		this.customerId = customerId;
		this.errors = errors;
		this.success = success;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
