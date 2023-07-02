package com.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Doctor;
import com.project.entities.Patient;
import com.project.services.PatientService;

@RestController
@RequestMapping("patient")
public class PatientController {
	@Autowired
	private PatientService patService;

	@PostMapping("create")
	public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {

		Patient pat = patService.createPat(patient);

		return ResponseEntity.status(HttpStatus.CREATED).body(pat);
	}
	
	@GetMapping("list")
	public ResponseEntity<List<Patient>> getPatientList() {
		List<Patient> patList = patService.getAll();
		if (patList.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(patList);
	}
	
	
}
