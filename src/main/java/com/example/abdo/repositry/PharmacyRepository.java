package com.example.abdo.repositry;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.abdo.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
	List<Pharmacy> findByPharmacyName(String name);

}
