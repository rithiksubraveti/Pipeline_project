package com.car.showroom.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.car.showroom.model.Dealers;
import com.car.showroom.repository.CarRepository;
import com.car.showroom.service.CarService;


@RestController
public class CarController {
	@Autowired
	CarRepository carRepo;
	@Autowired
	CarService carService;

	// Get list of all the dealers in the DB
	@GetMapping("/dealers")
	public ResponseEntity<List<Dealers>> getDealers() {
		List<Dealers> dealers = carService.getDealers();
		return new ResponseEntity<List<Dealers>>(dealers,HttpStatus.FOUND);
	}
	
	//Testing of pipeline
	// Get dealer based on Id
	@GetMapping("/dealers/{id}")
	public ResponseEntity<Dealers> getDealerById(@PathVariable(value = "id") long dealerId) {
		try
		{
			Dealers dealers = carService.getDealerById(dealerId);
			return new ResponseEntity<Dealers>(dealers,HttpStatus.FOUND);
					//ResponseEntity.ok().body(dealer.get());
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Adding new dealer to the DB
	@PostMapping("/addDealers")
	public ResponseEntity<Dealers> createNewDealer(@RequestBody Dealers dealers){
		try 
		{
			dealers = carService.createNewDealer(dealers);
			return new ResponseEntity<Dealers>(dealers,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	// Update dealer details in the DB
	
	  @PutMapping("/updatedealers/{id}") 
	  public ResponseEntity<Dealers> updateDealer(@PathVariable(value="id")long dealerId, @RequestBody Dealers dealers)
	  {
		  try 
			{
				Dealers checkDealers = carService.getDealerById(dealerId);
				checkDealers.setDealerName(dealers.getDealerName());
				checkDealers.setDealerEmail(dealers.getDealerEmail());
				checkDealers.setDealerAddress(dealers.getDealerAddress());
				Dealers updated_dealer = carService.updateDealer(dealers);
				return new ResponseEntity<Dealers>(updated_dealer,HttpStatus.OK);
			}
		  catch(Exception e)
		  {
			  return new ResponseEntity<>(HttpStatus.CONFLICT);
		  }

	  }
	 
	// Deleting dealer details from the DB
	@DeleteMapping("/deleteDealers/{id}")
	public ResponseEntity<Dealers> deleteDealer(@PathVariable(value = "id")long dealerId)
	{
		Dealers dealer = null;
		try
		{
			dealer = carService.getDealerById(dealerId);
			carService.deleteDealer(dealer);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dealers>(dealer,HttpStatus.OK);
		
	}

}
