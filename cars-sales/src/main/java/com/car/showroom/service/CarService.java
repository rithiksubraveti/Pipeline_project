package com.car.showroom.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.car.showroom.model.Dealers;
import com.car.showroom.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	public List<Dealers> getDealers() {
		List<Dealers> dealers= carRepository.findAll();
		return dealers;
	}
	public Dealers getDealerById(@PathVariable long dealerId){
		List<Dealers> dealers = carRepository.findAll();
		Dealers dealer = null;
		for(Dealers d:dealers)
		{
			if(d.getId()==dealerId)
				dealer=d;
		}
		return dealer;
	
	}
	public Dealers createNewDealer(@RequestBody Dealers dealer)
	{
		dealer.setId(getMaxId());
		carRepository.save(dealer);
		return dealer;
	}
	
	public Dealers updateDealer(Dealers dealer)
	{
		  carRepository.save(dealer);
	  		return dealer;
	}
	public void deleteDealer(Dealers dealers)
	{
		carRepository.delete(dealers);
	}
	public long getMaxId() 
	{
		// TODO Auto-generated method stub
		return carRepository.findAll().size()+1;
	}

}
