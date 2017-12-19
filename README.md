# REST API Customer Service 

This is customer REST API allowing customers to manage their customer profile within a organization. It exposes Swagger API contract. It is built using Spring Boot JAVA based framework. 

This API is generic and can be used by mobile and web channel. 

## How to Build 
	
Goto the project location in commandline and
Execute the command 

	$ mvn clean package

## How to Test

Goto the project location in commandline and Execute the command 

	$ mvn test

## How to Run

The generated jar file will be in the target folder of project location and Execute the command 

	$ java -jar target/customer-api-0.0.1-SNAPSHOT.jar

# SWAGGER REST API

Once the application is running, you can access Swagger API contract via following URL. 

http://localhost:8080/swagger-ui.html


## Following APIs have been implemented

### Create Customer Resource
http://locahost:8080/customer/add
### Get Customer Resource
http://locahost:8080/customer/{customerId}
### Update Customer Resource
http://locahost:8080/customer/update/{customerId}
### Delete Customer Resource
http://locahost:8080/customer/delete/{customerId}

Here, {customerId} is the unique customer Id. 

	