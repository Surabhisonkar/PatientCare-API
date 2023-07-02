package com.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.DoctorDto;
import com.project.entities.Doctor;
import com.project.entities.Patient;
import com.project.services.DoctorService;
import com.project.services.PatientService;

@RestController
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private DoctorService docService;
	@Autowired
	private PatientService patientService;
//	@Autowired
//	private ModelMapper mapper;

//	@PostMapping("create")
//	public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto) {
//		@Valid
//		Doctor doc = mapper.map(doctorDto, Doctor.class);
//		docService.createDoc(doc);
//		DoctorDto doctorSend = mapper.map(doc, DoctorDto.class);
//		return ResponseEntity.status(HttpStatus.CREATED).body(doctorSend);
//	}

	@PostMapping("create")
	public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {

		Doctor doc = docService.createDoc(doctor);

		return ResponseEntity.status(HttpStatus.CREATED).body(doc);
	}

	@GetMapping("get-list")
	public ResponseEntity<List<Doctor>> getDoctorList() {
		List<Doctor> docList = docService.getAll();
		if (docList.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(docList);
	}
	
	@GetMapping("getById")
	public ResponseEntity<Doctor> getById(@RequestParam int id){
		Doctor doc=docService.getById(id);
		return ResponseEntity.ok(doc);
	}

	@GetMapping("listByCity")
	public ResponseEntity<List<Doctor>> getByCity(@RequestParam String city) {
		List<Doctor> docList = docService.getByCity(city);
		if (docList.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(docList);
	}
	
	@GetMapping("listByCityAndSpeciality")
	public ResponseEntity<List<Doctor>> getByCityAndSpeciality(@RequestParam String city,@RequestParam String speciality) {
		List<Doctor> docList = docService.getByCityAndSpeciality(city,speciality);
		if (docList.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(docList);
	}
	
	@PatchMapping("updateNumber")
	public ResponseEntity<Doctor> updateNumber(@RequestParam int id,@Valid @RequestBody Doctor newDoc){
		Doctor doc=docService.getDoctor(id);
		if(doc==null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		Doctor updatedDoc=docService.updateNumber(doc,newDoc);
		return ResponseEntity.ok(updatedDoc);
	}
	
	
	@DeleteMapping("deleteById")
	public ResponseEntity<Doctor> deleteDoctor(@RequestParam int id){
		Doctor doc=docService.getDoctor(id);
		if(doc==null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		docService.deleteDoctor(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(doc);
	}
	
	
	@GetMapping("findByLocationAndSymptom")
	public ResponseEntity<List<Doctor>> findByLocationAndSymptoms(@RequestParam int id){
		Patient patient = patientService.getPatient(id);
		
		if(patient == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		List<Doctor> doctors = docService.findDoctorsByLocationAndSymptom(patient.getCity(), patient.getSymptom());
		System.out.println(doctors);
		return ResponseEntity.ok(doctors);
	}
}
