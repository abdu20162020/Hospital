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

import com.example.abdo.entity.Department;
import com.example.abdo.entity.Doctor;
import com.example.abdo.entity.Hospital;
import com.example.abdo.entity.Pharmacy;
import com.example.abdo.repositry.DepartmentRepository;
import com.example.abdo.repositry.DoctorRepository;
import com.example.abdo.repositry.PharmacyRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/control")
@CrossOrigin(origins = "*")
public class DepartmenController {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@GetMapping("/all/department")
	@ApiOperation(value = "Gets all Dpartment of a table", notes = "Gets departments",response = Department.class, responseContainer = "List")
	public List<Department> getAllDpartment() {
		return departmentRepository.findAll();
		}
	@GetMapping("/department/{id}")
	public Department getDepartmentById(@PathVariable("id") int id) {
		Optional<Department> dep=departmentRepository.findById(id);
		Department department=dep.get();	
		return department;
	}
	@GetMapping("/department/name/{name}")
	public List<Department> getPharmacyByName(@PathVariable("name") String name) {
		List<Department> department= departmentRepository.findByDepartmentName(name);
		return department;
	}
	@PostMapping("/department/save")
	public Department saveDepartment(@RequestBody Department department) {
		
		return departmentRepository.save(department);
	}
	@PutMapping("/department/update/{id}")
	public Department updateDepartment(@PathVariable("id") int id,@RequestBody Department department) {
		Optional<Department> departmentOptional=departmentRepository.findById(id);
		Department departmentUpdate=departmentOptional.get();
		departmentUpdate.setDepartmentName(department.getDepartmentName());
		return departmentRepository.save(departmentUpdate);
	}
	@PostMapping("/hospital/add/doctor/{departmentId}")
	public Department insertDoctor(@PathVariable("departmentId") int departmentId,@RequestBody Doctor doctor) {
		Optional<Department> departmentOptional=departmentRepository.findById(departmentId);
		Department department=departmentOptional.get();
		department.addDoctor(doctor);
		return departmentRepository.save(department);
	}
	@DeleteMapping("/hospital/delete/department/{departmentId}/{doctorId}")
	public Department deleteDoctor(@PathVariable("departmentId") int departmentId,@PathVariable("doctorId") int doctorId) {
		Optional<Doctor>doctormentOptional=doctorRepository.findById(doctorId);
		Doctor doctor=doctormentOptional.get();
		Optional<Department> departmentOptional=departmentRepository.findById(departmentId);
		Department department=departmentOptional.get();
		department.removeDoctor(doctor);
		return departmentRepository.save(department);	
	}

}
