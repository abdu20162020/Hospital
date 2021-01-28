package com.example.abdo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int doctorId;
	String doctorName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId")
	@JsonIgnore
	private Department department;
	@ManyToMany(mappedBy="doctors",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Patient> patients= new ArrayList<>();
	@ElementCollection
	@CollectionTable(name = "doctor_has_qulifications")
	private Set<String> qulifications;
	@Column(unique = true)
	String email;
	@Column(nullable = false)
	String gender;
	int age;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getQulifications() {
		return qulifications;
	}
	public void setQulifications(Set<String> qulifications) {
		this.qulifications = qulifications;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void addPatient(Patient patient) {
	    this.patients.add(patient);
	    patient.getDoctors().add(this);
	}
	
	public void removePatient(Patient patient) {
	    this.patients.remove(patient);
	    patient.getDoctors().remove(this);
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	

}
