package com.project.services;

import java.util.List;

import com.project.entities.Doctor;

public interface DoctorService {

	Doctor createDoc(Doctor doctor);

	List<Doctor> getAll();


	List<Doctor> getByCity(String city);

	List<Doctor> getByCityAndSpeciality(String city, String speciality);

	Doctor getDoctor(int id);

	Doctor updateNumber(Doctor doc, Doctor newDoc);

	Doctor getById(int id);

	void deleteDoctor(int id);

	List<Doctor> findDoctorsByLocationAndSymptom(String city, String symptom);

	
	
}
