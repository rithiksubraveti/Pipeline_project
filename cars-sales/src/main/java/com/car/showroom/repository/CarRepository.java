package com.car.showroom.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.car.showroom.model.Dealers;

@Repository
public interface CarRepository extends JpaRepository<Dealers,Long> 
{

}
