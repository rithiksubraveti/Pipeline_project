package com.car.showroom;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.car.showroom.model.Dealers;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTests {
	
	@Test
	@Order(1)
	void getDealersIntegrationTest() throws JSONException 
	{
		String expected = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"dealerName\": \"18 Autos\",\r\n"
				+ "        \"dealerEmail\": \"info@18autos.com\",\r\n"
				+ "        \"dealerAddress\": \"Tramore Road Co.Cork\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"dealerName\": \"2v Motor Sales\",\r\n"
				+ "        \"dealerEmail\": \"sales@2vmotors.ie\",\r\n"
				+ "        \"dealerAddress\": \"Kilreesk K67 R284 Co.Dublin\"\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:8080/dealers", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(2)
	void getDealersByIdIntegrationTest() throws JSONException 
	{
		String expected =  "{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"dealerName\": \"18 Autos\",\r\n"
				+ "        \"dealerEmail\": \"info@18autos.com\",\r\n"
				+ "        \"dealerAddress\": \"Tramore Road Co.Cork\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:8080/dealers/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(3)
	void addDealersIntegrationTest() throws JSONException 
	{
		Dealers dealer = new Dealers(3,"RonanCars","ronan@cars.ie","Cork");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"dealerName\": \"RonanCars\",\r\n"
				+ "        \"dealerEmail\": \"ronan@cars.ie\",\r\n"
				+ "        \"dealerAddress\": \"Cork\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Dealers> request = new HttpEntity<Dealers>(dealer,headers);
		ResponseEntity<String>response = restTemplate.postForEntity("http://localhost:8080/addDealers", request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(4)
	void updateDealersIntegrationTest() throws JSONException 
	{
		Dealers dealer = new Dealers(3,"Ray Automobiles","ray@carservice.ie","Dublin");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"dealerName\": \"Ray Automobiles\",\r\n"
				+ "        \"dealerEmail\": \"ray@carservice.ie\",\r\n"
				+ "        \"dealerAddress\": \"Dublin\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Dealers> request = new HttpEntity<Dealers>(dealer,headers);
		
		ResponseEntity<String>response = restTemplate.exchange("http://localhost:8080/updatedealers/3",HttpMethod.PUT, request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(5)
	void deleteDealersIntegrationTest() throws JSONException 
	{
		Dealers dealer = new Dealers(3,"Ray Automobiles","ray@carservice.ie","Dublin");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"dealerName\": \"Ray Automobiles\",\r\n"
				+ "        \"dealerEmail\": \"ray@carservice.ie\",\r\n"
				+ "        \"dealerAddress\": \"Dublin\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Dealers> request = new HttpEntity<Dealers>(dealer,headers);
		
		ResponseEntity<String>response = restTemplate.exchange("http://localhost:8080/deleteDealers/3",HttpMethod.DELETE, request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	

}
