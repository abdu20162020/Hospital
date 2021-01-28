package com.example.abdo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.abdo.entity.Department;
import com.example.abdo.entity.Doctor;
import com.example.abdo.entity.Patient;
import com.example.abdo.repositry.DoctorRepository;


@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {
	@Autowired
	DoctorRepository doctorRepository;
	@GetMapping("/all/doctor")
	public List<Doctor> getAllDoctor() {
		return doctorRepository.findAll();
		}
	@GetMapping("/doctors/{id}")
	public Doctor getDoctortById(@PathVariable("id") int id) {
		Optional<Doctor> doc=doctorRepository.findById(id);
		Doctor doctor=doc.get();	
		return doctor;
	}
	@GetMapping("/doctors/name/{name}")
	public List<Doctor> getDoctorByName(@PathVariable("name") String name) {
		List<Doctor> doctor= doctorRepository.findByDoctorName(name);
		return doctor;
	}
	@GetMapping("/doctors/email/{name}")
	public Doctor getDoctorByEmail(@PathVariable("name") String name) {
		Doctor doctor= doctorRepository.findByEmail(name);
		return doctor;
	}
	@GetMapping("/doctor/gender/{name}")
	public List<Doctor> getDoctorByGender(@PathVariable("name") String name) {
		List<Doctor> doctors= doctorRepository.findByGender(name);
		return doctors;
	}
	@GetMapping("/doctor/namegender/{name}/{gender}")
	public List<Doctor> getDoctorByGenderAndName(@PathVariable("name") String name,@PathVariable("gender") String gender) {
		List<Doctor> doctors= doctorRepository.findByDoctorNameAndGender(name, gender);
		return doctors;
	}
	@GetMapping("/doctor/top10")
	public List<Doctor> getTopTenDoctorByAge() {
		List<Doctor> doctors= doctorRepository.findTop10ByOrderByAgeDesc();
		return doctors;
	}
	@PostMapping("/doctor/save")
	public Doctor saveDoctor(@RequestBody Doctor doctor) {
		
		return doctorRepository.save(doctor);
	}
	@PutMapping("/doctor/update/{id}")
	public Doctor updateDoctor(@PathVariable("id") int id,@RequestBody Doctor doctor) {
		Optional<Doctor> doctorOptional=doctorRepository.findById(id);
		Doctor doctorUpdate=doctorOptional.get();
		doctorUpdate.setAge(doctor.getAge());
		doctorUpdate.setDoctorName(doctor.getDoctorName());
		doctorUpdate.setEmail(doctor.getEmail());
		doctorUpdate.setGender(doctor.getGender());
		return doctorRepository.save(doctorUpdate);
	}
	@PostMapping("/doctor/add/patient/{doctorId}")
		public Doctor inserPatient(@PathVariable("doctorId") int doctorId,@RequestBody Patient patient ) {
		Optional<Doctor> doctorOptional=doctorRepository.findById(doctorId);
		Doctor doctorUpdate=doctorOptional.get();
		doctorUpdate.addPatient(patient);
		return doctorRepository.save(doctorUpdate);
		
	}
}
