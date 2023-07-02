package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
