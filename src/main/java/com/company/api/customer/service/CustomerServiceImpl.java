package com.company.api.customer.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.company.api.customer.error.ResourceNotFoundException;
import com.company.api.customer.model.Address;
import com.company.api.customer.model.Customer;

/**
 * TODO This is mock Customer Service and needs to change implementation to call actual CRM backend service. 
 * @author Bhavin Mehta
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	static Map<String, Customer> customerMap = new HashMap<String, Customer>();
	
	/*Initial data load*/
	static {
		Address address = new Address("Glenfield Roead, Glenfield","Central","user@gmail.com");
		String uniqueKey = UUID.randomUUID().toString();
		Customer customer = new Customer(uniqueKey,"user name","user lastname",LocalDate.now(),address, null);
		customerMap.put(uniqueKey, customer);

	}
	
	@Override
	public Customer findByCustomerId(String customerId) {
		Customer customer = customerMap.get(customerId);
		if(customer==null){
			throw new ResourceNotFoundException(customerId,"customer not found");
		}
		
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		String uniqueKey = UUID.randomUUID().toString();
		customer.setCustomerId(uniqueKey);
		customerMap.put(uniqueKey, customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer, String customerId) {
		Customer oldCustomer = customerMap.get(customerId);
		if(oldCustomer==null){
			throw new ResourceNotFoundException(customerId,"customer not found");
		}
		customerMap.put(customerId, customer);
		return customer;
	}

	@Override
	public boolean deleteCustomer(String customerId) {
		Customer oldCustomer = customerMap.get(customerId);
		if(oldCustomer==null){
			throw new ResourceNotFoundException(customerId,"customer not found");
		}
		customerMap.remove(customerId);
		return true;
	}

}
