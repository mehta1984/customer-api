package com.company.api.customer.service;

import com.company.api.customer.model.Customer;

public interface CustomerService {

	public Customer findByCustomerId(String customerId);
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer, String customerId);
	public boolean deleteCustomer(String customerId);
}
