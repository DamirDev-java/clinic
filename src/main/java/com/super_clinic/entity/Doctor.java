package com.super_clinic.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "id")
@ToString(exclude = {"patients"})
public class Doctor extends User{
	
	@AttributeOverride(name = "name", column = @Column(name = "name"))
	PersonalInfo personalInfo;
	
	@Column(name = "is_ready")
	private boolean isReady;
	
	@Column(name = "specialisation")
	private String specialisation;
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
	private List<DoctorService> doctorServices = new ArrayList<>();
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
	private List<Appointment> appointments;

	
	@Builder
	public Doctor(Long id, String username, String password, List<DoctorService> doctorServices, PersonalInfo personalInfo, boolean isReady, String specialisation,  List<Patient> patients, List<UserRole> userRoles, List<Appointment> appointments) {
		super(id, username, password,"", "",userRoles);
		this.personalInfo = personalInfo;
		this.isReady = isReady;
		this.specialisation = specialisation;
		this.patients = patients;
		this.doctorServices = doctorServices;
		this.appointments = appointments;
	}


	
    
}
