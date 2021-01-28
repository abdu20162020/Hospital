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
import com.example.abdo.entity.Patient;
import com.example.abdo.repositry.PatientRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientController {
	@Autowired
	PatientRepository patientRepository;
	@GetMapping("/all/patient")
	@ApiOperation(value = "Gets all Patient of a table", notes = "Gets patients",response = Patient.class, responseContainer = "List")
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
		}
	@GetMapping("/patient/{id}")
	public Patient getPatientById(@PathVariable("id") int id) {
		Optional<Patient> pat=patientRepository.findById(id);
		Patient patient=pat.get();	
		return patient;
	}
	@GetMapping("/patient/name/{name}")
	public List<Patient> getPatientByName(@PathVariable("name") String name) {
		List<Patient> patient= patientRepository.findByPatientName(name);
		return patient;
	}
	@PostMapping("/patient/save")
	public Patient savePatient(@RequestBody Patient patient) {
		
		return patientRepository.save(patient);
	}
	@PutMapping("/patients/update/{id}")
	public Patient updatePatient(@PathVariable("id") int id,@RequestBody Patient patient) {
		Optional<Patient> patientOptional=patientRepository.findById(id);
		Patient patientUpdate=patientOptional.get();
		patientUpdate.setPatientName(patient.getPatientName());
		patientUpdate.setEscorts(patient.getEscorts());
		patientUpdate.setAge(patient.getAge());
		patientUpdate.setGender(patient.getGender());
		patientUpdate.setPatientRecord(patient.getPatientRecord());
		patientUpdate.setAddress(patient.getAddress());
		return patientRepository.save(patientUpdate);
	}
	

}
