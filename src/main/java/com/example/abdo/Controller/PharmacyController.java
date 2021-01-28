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

import com.example.abdo.entity.Doctor;
import com.example.abdo.entity.Hospital;
import com.example.abdo.entity.Pharmacy;
import com.example.abdo.repositry.PharmacyRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pharm")
@CrossOrigin(origins = "*")
public class PharmacyController {
	@Autowired
	PharmacyRepository pharmacyRepository;
	@GetMapping("/all/pharmacy")
	@ApiOperation(value = "Gets all Pharmacy of a table", notes = "Gets pharmacies",response = Pharmacy.class, responseContainer = "List")
	public List<Pharmacy> getAllPharmacy() {
		return pharmacyRepository.findAll();
	}

	@GetMapping("/pharmacy/{id}")
	public Pharmacy getPharmacyById(@PathVariable("id") int id) {
		Optional<Pharmacy> pharm=pharmacyRepository.findById(id);
		Pharmacy pharmacy=pharm.get();	
		return pharmacy;
	}

	@GetMapping("/pharmacy/name/{name}")
	public List<Pharmacy> getPharmacyByName(@PathVariable("name") String name) {
		List<Pharmacy> pharmacy= pharmacyRepository.findByPharmacyName(name);
		return pharmacy;
	}

	@PostMapping("/pharmacy/save")
	public Pharmacy savePharmacy(@RequestBody Pharmacy pharmacy) {
		return pharmacyRepository.save(pharmacy);
	}

	@PutMapping("/pharmacy/update/{id}")
	public Pharmacy updatePharmacy(@PathVariable("id") int id,@RequestBody Pharmacy pharmacy) {
		Optional<Pharmacy> pharmacyOptional=pharmacyRepository.findById(id);
		Pharmacy pharmacyUpdate=pharmacyOptional.get();
		pharmacyUpdate.setPharmacyName(pharmacy.getPharmacyName());
		pharmacyUpdate.setImageUrl(pharmacy.getImageUrl());
		return pharmacyRepository.save(pharmacyUpdate);
	}
	
}
	
