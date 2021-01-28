package com.example.abdo.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.abdo.entity.Hospital;
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	
	List<Hospital> findByHospitalName(String name);

}
