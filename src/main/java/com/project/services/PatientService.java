package com.project.services;

import java.util.List;

import com.project.entities.Patient;

public interface PatientService {

	Patient createPat( Patient patient);

	Patient getPatient(int id);

	List<Patient> getAll();

}
