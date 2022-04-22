package com.car.showroom;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.car.showroom.controller.CarController;
import com.car.showroom.model.Dealers;
import com.car.showroom.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan(basePackages="com.restservices.showroom")
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMockMvcTests.class})
public class ControllerMockMvcTests {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CarService carService;
	
	@InjectMocks
	CarController carController;
	
	List<Dealers> myDealers;
	Dealers dealers;
	
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
	}
	@Test
	@Order(1)
	public void test_getAllDealers() throws Exception
	{
		myDealers = new ArrayList<Dealers>();
		myDealers.add(new Dealers(1,"AutoCars","auto@cars.ie","Dublin"));
		myDealers.add(new Dealers(2,"RonanCars","ronan@cars.ie","Cork"));
		//Mocking		
		when (carService.getDealers()).thenReturn(myDealers);
		this.mockMvc.perform(get("/dealers")).andExpect(status().isFound()).andDo(print());
	}
	@Test
	@Order(2)
	public void test_getDealersById() throws Exception
	{
		dealers = new Dealers(1,"AutoCars","auto@cars.ie","Dublin");
		long dealerId=1;
		//Mocking		
		when (carService.getDealerById(dealerId)).thenReturn(dealers);
		this.mockMvc.perform(get("/dealers/{id}",dealerId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".dealerName").value("AutoCars"))
		.andExpect(MockMvcResultMatchers.jsonPath(".dealerEmail").value("auto@cars.ie"))
		.andExpect(MockMvcResultMatchers.jsonPath(".dealerAddress").value("Dublin"))
		.andDo(print());
	}
	@Test
	@Order(3)
	public void test_addDealers() throws Exception
	{
		dealers = new Dealers(3,"Jennigs Autocars","jennings@autocars.ie","Dublin");
		//Mocking		
		when (carService.createNewDealer(dealers)).thenReturn(dealers);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(dealers);
		this.mockMvc.perform(post("/addDealers")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	@Test
	@Order(4)
	public void test_updateDealer() throws Exception
	{
		dealers = new Dealers(3,"Ray Automobiles","ray@carservice.ie","Dublin");
		long dealerId=3;
		//Mocking		
		when (carService.getDealerById(dealerId)).thenReturn(dealers);
		when (carService.updateDealer(dealers)).thenReturn(dealers);
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonBody = mapper.writeValueAsString(dealers);
		this.mockMvc.perform(put("/updatedealers/{id}",dealerId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	@Test
	@Order(5)
	public void test_deleteDealers() throws Exception
	{
		
		dealers = new Dealers(1,"AutoCars","auto@cars.ie","Dublin");
		long dealerId=1;
		//Mocking		
		when (carService.getDealerById(dealerId)).thenReturn(dealers);
		this.mockMvc.perform(delete("/deleteDealers/{id}",dealerId))
				.andExpect(status().isOk());
	}
	
}
