package com.company.api.customer.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.api.customer.error.ApiError;
import com.company.api.customer.model.CustomerResponse;
import com.company.api.customer.service.CustomerService;
import com.company.api.customer.service.CustomerServiceImpl;
import com.google.gson.Gson;

@WebMvcTest(CustomerController.class)
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@TestConfiguration
    static class CustomerControllerTestContextConfiguration {
  
        @Bean
        public CustomerService employeeService() {
            return new CustomerServiceImpl();
        }
    }
	
	static String customerId = "";
	
	@Before
	public void executedBeforeClass() throws Exception {
		
		String addCustomerInputJson = "{" + 
				"  \"address\": {" + 
				"    \"email\": \"user@hotmail.com\"," + 
				"    \"home\": \"304 George St,sydney, NSW, 2000\"," + 
				"    \"office\": \"\"" + 
				"  }," + 
				"  \"customAttributes\": {}," + 
				"  \"customerId\": \"\"," + 
				"  \"dob\": \"1991-12-30\"," + 
				"  \"firstname\": \"user1\"," + 
				"  \"lastname\": \"marsh\"" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/add").accept(MediaType.APPLICATION_JSON)
				.content(addCustomerInputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		Gson gson = new Gson();
		CustomerResponse customerResponse = gson.fromJson(result.getResponse().getContentAsString(),
				CustomerResponse.class);
		
		customerId = customerResponse.getCustomerId();
	}

	
	@Test
	public void addCustomer() throws Exception{
		
		String addCustomerInputJson = "{" + 
				"  \"address\": {" + 
				"    \"email\": \"test@gmail.com\"," + 
				"    \"home\": \"200 George St,sydney, NSW, 2000\"," + 
				"    \"office\": \"\"" + 
				"  }," + 
				"  \"customAttributes\": {}," + 
				"  \"customerId\": \"\"," + 
				"  \"dob\": \"2001-12-30\"," + 
				"  \"firstname\": \"username\"," + 
				"  \"lastname\": \"usersurname\"" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/add").accept(MediaType.APPLICATION_JSON)
				.content(addCustomerInputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		//Verify the HTTPStatus 
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		Gson gson = new Gson();
		//Get Response object from JSON result 
		CustomerResponse customerResponse = gson.fromJson(result.getResponse().getContentAsString(),
				CustomerResponse.class);

		assertNotNull(customerResponse);
		assertNotNull(customerResponse.getCustomerId());
		assertTrue(customerResponse.isSuccess());
		assertEquals(null,customerResponse.getErrors());
	}
	
	
	@Test
	public void udpateCustomer() throws Exception{
		
		String updateCustomerInputJson = "{" + 
				"  \"address\": {" + 
				"    \"email\": \"mark@gmail.com\"," + 
				"    \"home\": \"701 George St,sydney, NSW, 2000\"," + 
				"    \"office\": \"\"" + 
				"  }," + 
				"  \"customAttributes\": {}," + 
				"  \"customerId\": \""+customerId+"\"," + 
				"  \"dob\": \"2001-12-30\"," + 
				"  \"firstname\": \"username\"," + 
				"  \"lastname\": \"usersurname\"" + 
				"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/update/"+customerId).accept(
				MediaType.APPLICATION_JSON).content(updateCustomerInputJson).contentType(MediaType.APPLICATION_JSON);;
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		String expected = "{\"customerId\":\""+customerId+"\",\"firstname\":\"username\",\"lastname\":\"usersurname\",\"dob\":\"2001-12-30\",\"address\":{\"home\":\"701 George St,sydney, NSW, 2000\",\"office\":\"\",\"email\":\"mark@gmail.com\"},\"customAttributes\":{}}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void deleteCustomer() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/customer/delete/"+customerId).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}
	
	@Test
	public void getCustomer() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/customer/"+customerId).accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		String expected = "{\"customerId\":\""+customerId+"\",\"firstname\":\"user1\",\"lastname\":\"marsh\",\"dob\":\"1991-12-30\",\"address\":{\"home\":\"304 George St,sydney, NSW, 2000\",\"office\":\"\",\"email\":\"user@hotmail.com\"},\"customAttributes\":{}}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void addCustomerInvalidInput() throws Exception{
		
		String addCustomerInputJson = "{" + 
				"  \"address\": {" + 
				"    \"email\": \"test@gmail.com\"," + 
				"    \"home\": \"200 George St,sydney, NSW, 2000\"," + 
				"    \"office\": \"\"" + 
				"  }," + 
				"  \"customAttributes\": {}," + 
				"  \"customerId\": \"\"," + 
				"  \"dob\": \"2001--30\"," + 
				"  \"firstname\": \"username\"," + 
				"  \"lastname\": \"usersurname\"" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/add").accept(MediaType.APPLICATION_JSON)
				.content(addCustomerInputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		//Verify the HTTPStatus 
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

		Gson gson = new Gson();
		//Get Response object from JSON result 
		ApiError apiError = (ApiError) gson.fromJson(result.getResponse().getContentAsString(),
				ApiError.class);

		assertNotNull(apiError);
	}
	
	@Test
	public void getCustomerResourceNotFound() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/customer/xxxx").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}
}
