package com.example.abdo.entity;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "This is Model for Address")
@Embeddable
public class Address {
//	@ApiModelProperty(notes = "the Sreet name",required = true)
	private String street;
	private String buildingNumber;
	private String city;
	
	public Address() {
	}
	public Address(String street, String buildingNumber, String city) {
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
