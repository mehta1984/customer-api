package com.company.api.customer.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.api.customer.error.ApiError;
import com.company.api.customer.model.Customer;
import com.company.api.customer.model.CustomerResponse;
import com.company.api.customer.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@Api(value = "REST API for Customer operation", tags = "Customer API")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Get Customer Resource")
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response=ApiError.class),
	        @ApiResponse(code = 400, message = "The bad request",response=ApiError.class)
	}
	)
	public Customer getCustomer(@PathVariable String customerId ) {
		return customerService.findByCustomerId(customerId);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST,  produces = "application/json")
	@ApiOperation(value = "Create Customer Resource")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully Created a resource"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 400, message = "The bad request",response=ApiError.class)
	}
	)
	public ResponseEntity<CustomerResponse>  saveCustomer(@Valid @RequestBody Customer customer) {
		Customer saveCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<CustomerResponse>( new CustomerResponse(saveCustomer.getCustomerId(),null ,true),HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{customerId}", method = RequestMethod.PUT,  produces = "application/json")
	@ApiOperation(value = "Update Customer Resource")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated a resource"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response=ApiError.class),
	        @ApiResponse(code = 400, message = "The bad request",response=ApiError.class)
	}
	)
	public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
		customerService.updateCustomer(customer, customerId);
		return new ResponseEntity<Customer>( customer,HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE,  produces = "application/json")
	@ApiOperation(value = "Delete Customer Resource")
	@ApiResponses(value = {
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found",response=ApiError.class),
	        @ApiResponse(code = 400, message = "The bad request",response=ApiError.class)
	}
	)
	public ResponseEntity<?> delete(@PathVariable String customerId) {
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	


}
