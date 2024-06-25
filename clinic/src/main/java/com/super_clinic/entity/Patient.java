package com.super_clinic.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User{
	
	private PersonalInfo personalInfo;
	
	private String diagnosis;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;

	@Builder
	public Patient(Long id, String username, String password, PersonalInfo personalInfo, String diagnosis, Doctor doctor, List<UserRole> userRoles) {
		super(id, username, password, userRoles);
		this.personalInfo = personalInfo;
		this.diagnosis = diagnosis;
		this.doctor = doctor;
	}
	
	
}
