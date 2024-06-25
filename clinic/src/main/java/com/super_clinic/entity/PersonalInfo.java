package com.super_clinic.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@NotNull
	private String surname;
	
	private String photoUrl;
	
}
