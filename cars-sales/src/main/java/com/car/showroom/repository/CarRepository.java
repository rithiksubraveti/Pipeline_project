package com.car.showroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.showroom.model.Cars;
import com.car.showroom.model.Dealers;

@Repository
public interface CarRepository extends JpaRepository<Dealers,Long> 
{
	List<Dealers> findByName(String dealerNname);
}
