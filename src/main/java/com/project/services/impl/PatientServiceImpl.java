package com.project.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Patient;
import com.project.repositories.PatientRepository;
import com.project.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patRepo;

	public PatientRepository getPatRepo() {
		return patRepo;
	}


	public void setPatRepo(PatientRepository patRepo) {
		this.patRepo = patRepo;
	}


	@Override
	public Patient createPat(Patient patient) {
		return patRepo.save(patient);
	}


	public Patient getPatient(int id) {
		return patRepo.findById(id).orElse(null);
	}


	@Override
	public List<Patient> getAll() {

		return patRepo.findAll();
	}
}
