package com.project;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.controllers.DoctorController;
import com.project.entities.Doctor;
import com.project.services.DoctorService;

@WebMvcTest(DoctorController.class)
public class ControllerTest {
	@MockBean
	DoctorService doctorService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void createDoctor() throws Exception
	{
		Doctor doctor = new Doctor();
		
		when(doctorService.createDoc(doctor)).thenReturn(doctor);
		
		mockMvc.perform(post("doctor/create").contentType(MediaType.APPLICATION_JSON)
				.content("{ \" fname\" : \" Ritish\" ,\" lname\" : \" Srivastava\"}"))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.fname", is("Ritish")))
		.andExpect(jsonPath("$.lname", is("Srivastava")));
		
			 
	}
	

}
