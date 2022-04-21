package com.car.showroom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.car.showroom.controller.CarController;
import com.car.showroom.model.Dealers;
import com.car.showrooom.service.CarService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMackitoTests.class})
public class ControllerMackitoTests 
{
	@Mock
	CarService carService;
	
	@InjectMocks
	CarController carController;
	
	List<Dealers> myDealers;
	Dealers dealer;
	
	//Testing controller methods
	
	@Test
	@Order(1)
	public void test_getAllDealers()
	{
		myDealers = new ArrayList<Dealers>();
		myDealers.add(new Dealers(1,"AutoCars","auto@cars.ie","Dublin"));
		myDealers.add(new Dealers(2,"RonanCars","ronan@cars.ie","Cork"));
		//Mocking		
		when (carService.getDealers()).thenReturn(myDealers);
		ResponseEntity<List<Dealers>>response= carController.getDealers();
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.FOUND,response.getStatusCode());
		//checking the number of records
		assertEquals(2,response.getBody().size());
	}
	@Test
	@Order(2)
	public void test_getDealersById()
	{
		dealer = new Dealers(1,"AutoCars","auto@cars.ie","Dublin");
		long dealerId=1;
		//Mocking		
		when (carService.getDealerById(dealerId)).thenReturn(dealer);
		ResponseEntity<Dealers>response= carController.getDealerById(dealerId);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.FOUND,response.getStatusCode());
		assertEquals(dealerId,response.getBody().getId());
	}
	@Test
	@Order(3)
	public void test_addDealers()
	{
		dealer = new Dealers(3,"Jennigs Autocars","jennings@autocars.ie","Dublin");
		
		//Mocking		
		when (carService.createNewDealer(dealer)).thenReturn(dealer);
		ResponseEntity<Dealers>response= carController.createNewDealer(dealer);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals(dealer,response.getBody());
	}
	@Test
	@Order(4)
	public void test_updateDealers()
	{
		
		dealer = new Dealers(3,"Ray Automobiles","ray@carservice.ie","Dublin");
		long dealerId=3;
		//Mocking		
		when (carService.getDealerById(dealerId)).thenReturn(dealer);
		when (carService.updateDealer(dealer)).thenReturn(dealer);
		ResponseEntity<Dealers>response= carController.updateDealer(dealerId,dealer);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,response.getBody().getId());
		assertEquals("Ray Automobiles",response.getBody().getDealerName());
		assertEquals("ray@carservice.ie",response.getBody().getDealerEmail());
		assertEquals("Dublin",response.getBody().getDealerAddress());
	}
	@Test
	@Order(5)
	public void test_deleteDealers()
	{
		
		dealer = new Dealers(3,"Ray Automobiles","ray@carservice.ie","Dublin");
		long id=3;
		//Mocking		
		when (carService.getDealerById(id)).thenReturn(dealer);
		ResponseEntity<Dealers>response= carController.deleteDealer(id);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,response.getBody().getId());
		assertEquals("Ray Automobiles",response.getBody().getDealerName());
		assertEquals("ray@carservice.ie",response.getBody().getDealerEmail());
		assertEquals("Dublin",response.getBody().getDealerAddress());
	}

}
