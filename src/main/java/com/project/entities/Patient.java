package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "patients	")
public class Patient {
	@Id
	@GeneratedValue
	private int id;

	@NotNull(message = "Name cannot be null")
	@Pattern(regexp = "[A-Za-z' ']*", message = "only characters allowed")
	@Length(min = 3, message = "Length too Small")
	private String name;

	@NotNull(message = "City cannot be empty")
	@Pattern(regexp = "Noida|Srinagar|Delhi|Lucknow", message = "Invalid City")
	@Length(max = 20, message = "Max Length of City Reached")
	private String city;

	@NotNull
	@NotNull(message = "Speciality cannot be empty")
	@Pattern(regexp = "Arthritis|Backpain|Tissue injuries|Dysmenorrhea|Skin infection|Skin burn|Ear pain", message = "Invalid Symptom")
	private String symptom;

	@NotNull
	@Email
	private String email;

	@NotNull(message = "Number cannot be empty")
	@Pattern(regexp = "[0-9]*", message = "Only digits allowed")
	@Length(min = 10, max = 10, message = "10 digits required")
	private String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
