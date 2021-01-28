package com.example.abdo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

	
@Entity
@Table(name = "Department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int departmentId;
	@Column(name = "Department_Name")
	String departmentName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospitalId")
	@JsonIgnore
	private Hospital hospital;
	@OneToMany(mappedBy = "department",
	cascade = CascadeType.ALL,
	fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Doctor> doctors= new ArrayList<>();
	public Hospital getHospital() {
		return hospital;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors.clear();
		if(doctors!=null)
		{
			for(Doctor doctor: doctors) {
				doctor.setDepartment(this);
			}
			this.doctors.addAll(doctors);
		}
	}
	public void addDoctor(Doctor doctor) {
		doctors.add(doctor);
		doctor.setDepartment(this);
	}
	public void removeDoctor(Doctor doctor) {
		doctors.remove(doctor);
		doctor.setDepartment(null);
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	}
