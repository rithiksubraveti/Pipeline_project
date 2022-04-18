package com.car.showroom.model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cars")
public class Cars 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
	@Column(name = "car_make")
    private String carMake;
	@Column(name = "car_model")
    private String carModel;
	@Column(name = "car_year")
    private int carYear;

    
    public Cars() 
    {

	}

	public Cars(String carMake, String carModel, int carYear) 
    {
  		
  		
  		this.carMake = carMake;
  		this.carModel = carModel;
  		this.carYear = carYear;
  	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getCarMake()
	{
		return carMake;
	}

	public void setCarMake(String carMake)
	{
		this.carMake = carMake;
	}

	public String getCarModel() 
	{
		return carModel;
	}

	public void setCarModel(String carModel) 
	{
		this.carModel = carModel;
	}
	public int getCarYear() 
	{
		return carYear;
	}

	public void setCarYear(int carYear)
	{
		this.carYear = carYear;
	}


	 
    

}

