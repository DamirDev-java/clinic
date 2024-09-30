package com.super_clinic.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@Column(name = "start_time")
	private LocalDateTime start;

	@Column(name = "end_time")
	private LocalDateTime end;

    public Appointment (LocalDateTime start, LocalDateTime end, boolean isFree) {
		this.start = start;
		this.end = end;
		this.isFree = isFree;
	}

	private boolean isFree;

}
