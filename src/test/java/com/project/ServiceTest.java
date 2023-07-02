package com.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.entities.Doctor;
import com.project.repositories.DoctorRepository;
import com.project.services.DoctorService;
import com.project.services.impl.DoctorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	
	 @InjectMocks
	 DoctorServiceImpl doctorService;
	 @Mock
	 DoctorRepository doctorRepository;
	 
	 
	 @Test
	 void createDoc_test()
	 {
		 Doctor doctor = new Doctor();
		 when(doctorRepository.save(doctor)).thenReturn(doctor);
		 
		 Doctor newDoc = doctorService.createDoc(doctor);
		 
		 assertThat(doctor).isEqualTo(newDoc);
	 }
	 @Test
	 void getAll_Test()
	 {
	     List<Doctor>list = new ArrayList<>();	 
	     
	     when(doctorRepository.findAll()).thenReturn(list);
	     
	     List<Doctor>actual = doctorService.getAll();
	     
	     assertThat(list).isEqualTo(actual);
	     
	 }
	 @Test
	 void getByCity() 
	 {
		 List<Doctor>expected = new ArrayList<>();
		 when(doctorRepository.findByCity("Delhi")).thenReturn(expected);
		 
		 List<Doctor>actual = doctorService.getByCity("Delhi");
		 assertThat(expected).isEqualTo(actual);
		 
	 }
}
