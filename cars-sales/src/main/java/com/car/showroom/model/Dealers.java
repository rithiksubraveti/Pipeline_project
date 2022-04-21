package com.car.showroom.model;

import java.util.List;

import javax.persistence.*;




@Entity
@Table(name = "dealers")
public class Dealers 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "dealer_name")
    private String dealerName;
    @Column(name = "dealer_email")
    private String dealerEmail;
    @Column(name = "dealer_address")
    private String dealerAddress;

	
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getDealerName()
	{
		return dealerName;
	}
	public void setDealerName(String dealerName) 
	{
		this.dealerName = dealerName;
	}
	public String getDealerEmail() 
	{
		return dealerEmail;
	}
	public void setDealerEmail(String dealerEmail) 
	{
		this.dealerEmail = dealerEmail;
	}
	public String getDealerAddress() 
	{
		return dealerAddress;
	}
	public void setDealerAddress(String dealerAddress) 
	{
		this.dealerAddress = dealerAddress;
	}

	 
	public Dealers(long dealerId, String dealerName, String dealerEmail, String dealerAddress)
	{
		this.id = dealerId;
		this.dealerName = dealerName;
		this.dealerEmail = dealerEmail;
		this.dealerAddress = dealerAddress;
	}
	public Dealers() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
}