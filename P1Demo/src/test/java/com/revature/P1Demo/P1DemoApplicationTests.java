package com.revature.P1Demo;

import com.revature.DAOs.CarDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.Car;
import com.revature.models.DTOs.IncomingCarDTO;
import com.revature.models.User;
import com.revature.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class P1DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	//A RestTemplate test for insertCar in the Controller------------------------------------------
	@Test
	public void insertCarTestRestController() {

		//Create a new IncomingCarDTO object
		IncomingCarDTO newCar = new IncomingCarDTO("Toyota", "Camry", false, 1);

		//Get a user by ID

		//Create a RestTemplate object
		RestTemplate restTemplate = new RestTemplate();

		//Send the newCar object to the insertCar endpoint
		//The endpoint will return a Car object
		Car returnedCar = restTemplate.postForObject("http://localhost:8080/cars", newCar, Car.class);

		//Check if the returnedCar object is not null
		assertNotNull(returnedCar);
	}


	//A mockito test for insert car in the car service----------------------

	//A lot of setup first...

	//First, we need mock DAOs, so we don't actually send a Car to the database
	@Mock
	CarDAO mockedCarDAO;
	@Mock
	UserDAO mockedUserDAO;

	//Next, we have a concrete instance of the Service, so we can "spy" on it with methods like verify()
	@Spy
	CarService cs = new CarService(mockedCarDAO, mockedUserDAO);

	//Regular IncomingCarDTO and Car objects to help with tests
	IncomingCarDTO newCar = new IncomingCarDTO("Toyota", "Camry", false, 1);
	Car car = new Car(1,"Toyota", "Camry", false, null);

	@BeforeEach
	public void setup(){
		//initialize fields annotated with @mock, @spy
		MockitoAnnotations.openMocks(this);

		//
		cs = spy(new CarService(mockedCarDAO, mockedUserDAO));
	}

	//Test that insert car returns the car object
	@Test
	public void insertCarTestMockito() {

		//When the CarDAO's save method is called with the car object, return the car object
		//This is a "stub" - a mock method call that returns a predictable output
		when(mockedCarDAO.save(any(Car.class))).thenReturn(car);

		//Another stub - when the UserDAO's findById method is called with 1, return an Optional with a User object
		when(mockedUserDAO.findById(1)).thenReturn(java.util.Optional.of(new User()));

		//Call the Service's addCar method with the newCar object
		Car returnedCar = cs.addCar(newCar);

		//verify that the DAO's save method was called
		verify(mockedCarDAO).save(any(Car.class));

		//Check that the returnedCar object is not null
		assertNotNull(returnedCar);
	}

	/* What's the point of writing a test if the Car, CarDTO, and User are predetermined?
	We're testing the LOGIC of the service layer, not what it gets from the DAO.
	The DAO is mocked, so we can focus on making sure the service layer works
	This becomes more important/useful as our service methods get huge with lots of error handling */



}
