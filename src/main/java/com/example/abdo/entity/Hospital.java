package com.example.abdo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



//TABLE AT DATABASE
@Entity
@Table(name = "Hospital")
public class Hospital {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int hospitalId;
	String hospitalName;
	@OneToMany(mappedBy = "hospital",
				cascade = CascadeType.ALL,
				fetch = FetchType.LAZY, orphanRemoval = true)
	
	private List<Department> departments= new ArrayList<>();
	@OneToMany(mappedBy = "hospital",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Pharmacy> pharmacies= new ArrayList<>();
	@Embedded
	private Address address;
	String imageUrl;
	
	public Hospital(String hospitalName, String imageUrl) {
		this.hospitalName = hospitalName;
		this.imageUrl = imageUrl;
	}
	public Hospital() {
	}
	public Hospital(String hospitalName, Address address, String imageUrl) {
		this.hospitalName = hospitalName;
		this.address = address;
		this.imageUrl = imageUrl;
	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}
	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies.clear();
		
		if(pharmacies!=null)
		{
			for(Pharmacy pharmacy: pharmacies) {
				pharmacy.setHospital(this);
			}
			this.pharmacies.addAll(pharmacies);
		}
	}
	public void addPharmacy(Pharmacy pharmacy) {
		pharmacies.add(pharmacy);
		pharmacy.setHospital(this);
    }
 
    public void removePharmacy(Pharmacy pharmacy) {
    	pharmacies.remove(pharmacy);
    	pharmacy.setHospital(null);
    }
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments.clear();
		if(departments!=null)
		{
			for(Department department: departments) {
				department.setHospital(this);
			}
			this.departments.addAll(departments);
		}
	}
	public void addDepartment(Department department) {
		departments.add(department);
		department.setHospital(this);
    }
 
    public void removeDepartment(Department department) {
    	departments.remove(department);
    	department.setHospital(null);
    }
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
