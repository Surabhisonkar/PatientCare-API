package com.project.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Doctor;
import com.project.repositories.DoctorRepository;
import com.project.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository docRepo;
	
	

	public DoctorRepository getDocRepo() {
		return docRepo;
	}

	public void setDocRepo(DoctorRepository docRepo) {
		this.docRepo = docRepo;
	}

	@Override
	public Doctor createDoc(Doctor doctor) {
		return docRepo.save(doctor);

	}

	@Override
	public List<Doctor> getAll() {
		return docRepo.findAll();
	}

	@Override
	public List<Doctor> getByCity(String city) {
		return docRepo.findByCity(city);
	}

	@Override
	public List<Doctor> getByCityAndSpeciality(String city, String speciality) {

		return docRepo.findByCityAndSpeciality(city, speciality);
	}

	@Override
	public Doctor getDoctor(int id) {

		return docRepo.findById(id).orElse(null);
	}

	@Override
	public Doctor updateNumber(Doctor doc, Doctor newDoc) {
		System.out.println(newDoc);
		doc.setPhoneNumber(newDoc.getPhoneNumber());
		docRepo.save(doc);
		return doc;
	}

	@Override
	public Doctor getById(int id) {
		return docRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteDoctor(int id) {
		docRepo.deleteById(id);
	}

	
	public List<Doctor> findDoctorsByLocationAndSymptom(String city, String symptom) {
		String sym = mapSymptomToSpecialty(symptom);
		return docRepo.findByCityAndSpeciality(city, sym);
	}
	
	private String mapSymptomToSpecialty(String symptom) {
		Map<String, String> myMap = new HashMap<String, String>() {{
		    put("Arthritis,", "Orthopedic");
		    put("Backpain,", "Orthopedic");
		    put("Dysmenorrhea,", "Gynecology");
		    put("Ear pain", "ENT");
		    
		}};
		
        return myMap.get(symptom);
    }

}
