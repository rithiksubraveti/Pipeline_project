package com.car.showroom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.showroom.exception.ResourceNotFoundException;
import com.car.showroom.model.Cars;
import com.car.showroom.model.Dealers;
import com.car.showroom.repository.CarRepository;

@RestController
class CarController {
	@Autowired
	public CarRepository carRepo;

	// Get list of all the cars in the DB
	@GetMapping("/dealers")
	public List<Dealers> getDealers() {
		return carRepo.findAll();
	}

	// Get Car based on Id
	@GetMapping("dealers/{id}")
	public ResponseEntity<Dealers> getDealerById(@PathVariable(value = "id") long dealerId) throws ResourceNotFoundException {
		Optional<Dealers> dealer = carRepo.findById(dealerId);
		if (dealer.isPresent())
			return ResponseEntity.ok().body(dealer.get());
		else
			throw new ResourceNotFoundException("No car found with Id: "+dealerId);
	}

	// Adding new car to the DB
	@PostMapping("/dealers")
	public Dealers createNewDealer(@RequestBody Dealers dealer) {
		return carRepo.save(dealer);
	}

	// Update car details in the DB
	
	  @PutMapping("dealers/{id}") 
	  public ResponseEntity updateDealer(@PathVariable(value="id")long dealerId, @RequestBody Dealers dealerDetails)throws ResourceNotFoundException 
	  {
		  Dealers dealer = carRepo.findById(dealerId).orElseThrow(() -> new ResourceNotFoundException("No car found with Id: " + dealerId));
		  dealer.setDealerName(dealerDetails.getDealerName());
		  dealer.setDealerEmail(dealerDetails.getDealerEmail());
		  dealer.setDealerAddress(dealer.getDealerAddress());
		  dealer.setCars(dealerDetails.getCars());
		  carRepo.save(dealer);
		  return ResponseEntity.ok().body(dealer);	  
	  }
	 
	// Deleting car details from the DB
	@DeleteMapping("/dealers/{id}")
	public ResponseEntity deleteDealer(@PathVariable(value = "id") long dealerId) throws ResourceNotFoundException 
	{
		Dealers dealer = carRepo.findById(dealerId).orElseThrow(() -> new ResourceNotFoundException("No dealer found with Id: " + dealerId));
		carRepo.deleteById(dealerId);
		return ResponseEntity.ok().build();
	}
	

}
