package com.example.abdo.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.abdo.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	List<Patient> findByPatientName(String name);
	List<Patient> findByPatientRecord(String name);


}
