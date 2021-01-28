package com.example.abdo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.abdo.entity.Address;
import com.example.abdo.entity.Department;
import com.example.abdo.entity.Hospital;
import com.example.abdo.entity.Pharmacy;
import com.example.abdo.repositry.DepartmentRepository;
import com.example.abdo.repositry.HospitalRepository;
import com.example.abdo.repositry.PharmacyRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")


public class HospitalController {
	@Autowired
	HospitalRepository hospitalRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	PharmacyRepository pharmacyRepository;
	
	
	@GetMapping("/all/hospital")
	@ApiOperation(value = "Gets all Hospital of a table", notes = "Gets hospitals",response = Hospital.class, responseContainer = "List")
	public List<Hospital> getAllHospital() {
		
		return hospitalRepository.findAll();
	}
	@GetMapping("/hospital/{id}")
	public Hospital getHosptialById(@PathVariable("id") int id) {
		Optional<Hospital> hospitalTemp=hospitalRepository.findById(id);
		Hospital hospital=hospitalTemp.get();	
		return hospital;
	}
	@GetMapping("/hospital/name/{name}")
	public List<Hospital> getHosptialByName(@PathVariable("name") String name) {
		List<Hospital> hospital= hospitalRepository.findByHospitalName(name);
		return hospital;
	}
	
	@PostMapping("/hospial/sava")
	public Hospital saveHospital(@RequestBody Hospital hospital) {
		
		return hospitalRepository.save(hospital);
	}
	@PutMapping("/hospital/update/{id}")
	public Hospital updateHospital(@PathVariable("id") int id,@RequestBody Hospital hospital) {
		
		Optional<Hospital> hospitalOptional=hospitalRepository.findById(id);
		Hospital hospitalUpdate=hospitalOptional.get();
		hospitalUpdate.setAddress(hospital.getAddress());
		hospitalUpdate.setHospitalName(hospital.getHospitalName());
		return hospitalRepository.save(hospitalUpdate);
	}
	@PostMapping("/hospital/add/pharmacy/{id}")
	public Hospital insertPharmacy(@PathVariable("id") int id,@RequestBody Pharmacy pharmacy) {
		Optional<Hospital> hospitalOptional=hospitalRepository.findById(id);
		Hospital hospital=hospitalOptional.get();
		hospital.addPharmacy(pharmacy);
		return hospitalRepository.save(hospital);
	}
	@PostMapping("/hospital/add/department/{id}")
	public Hospital insertDepartment(@PathVariable("id") int id,@RequestBody Department department) {
		Optional<Hospital> hospitalOptional=hospitalRepository.findById(id);
		Hospital hospital=hospitalOptional.get();
		hospital.addDepartment(department);
		return hospitalRepository.save(hospital);
	}
	@DeleteMapping("/hospital/delete/department/{hospitalId}/{departmentId}")
	public Hospital deleteDepartment(@PathVariable("hospitalId") int hospitalId,@PathVariable("departmentId") int departmentId) {
		Optional<Department> departmentOptional=departmentRepository.findById(departmentId);
		Department department=departmentOptional.get();
		Optional<Hospital> hospitalOptional=hospitalRepository.findById(hospitalId);
		Hospital hospital=hospitalOptional.get();
		hospital.removeDepartment(department);
		return hospitalRepository.save(hospital);	
	}
	@DeleteMapping("/hospital/delete/pharmacy/{hospitalId}/{pharmacyId}")
	public Hospital deletePharmacy(@PathVariable("hospitalId") int hospitalId,@PathVariable("pharmacyId") int pharmacyId) {
		Optional<Pharmacy> pharmacyOptional=pharmacyRepository.findById(pharmacyId);
		Pharmacy pharmacy=pharmacyOptional.get();
		Optional<Hospital> hospitalOptional=hospitalRepository.findById(hospitalId);
		Hospital hospital=hospitalOptional.get();
		hospital.removePharmacy(pharmacy);
		return hospitalRepository.save(hospital);	
	}


}
