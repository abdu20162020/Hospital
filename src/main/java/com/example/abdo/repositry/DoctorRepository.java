package com.example.abdo.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.abdo.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	List<Doctor> findByDoctorName(String name);
	List<Doctor> findByGender(String name);
	Doctor findByEmail(String name);
	List<Doctor> findByDoctorNameAndGender(String name,String gender);
    List<Doctor> findTop10ByOrderByAgeDesc();




}
