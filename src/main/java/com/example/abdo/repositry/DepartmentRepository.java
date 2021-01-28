package com.example.abdo.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.abdo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	List<Department> findByDepartmentName(String name);

}
