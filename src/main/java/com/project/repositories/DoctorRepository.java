package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	List<Doctor> findByCity(String city);

	List<Doctor> findByCityAndSpeciality(String city, String speciality);

	
}
