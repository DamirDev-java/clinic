package com.super_clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.super_clinic.dto.PatientDto;
import com.super_clinic.entity.Patient;
import com.super_clinic.mapper.PatientMapper;
import com.super_clinic.repository.DoctorRepository;
import com.super_clinic.repository.PatientRepository;
import com.super_clinic.service.BaseService;

import jakarta.persistence.EntityNotFoundException;

@Transactional
@Service
public class PatientServiceImpl extends BaseService<Long, PatientDto, Patient, PatientMapper, PatientRepository>{
	
    private DoctorRepository doctorRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public PatientServiceImpl(PatientRepository repository, PatientMapper mapper,  DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
		super(repository, mapper);
		this.doctorRepository = doctorRepository;
		this.passwordEncoder = passwordEncoder;
	}

  
	public Long save(PatientDto e) {
	    var patient = mapper.dtoToEntity(e);
	    
	    // Проверяем, не является ли e.getDoctorId() равным null
	    if (e.getDoctorId() != null) {
	        var doctorOptional = doctorRepository.findById(e.getDoctorId());
	        
	        // Проверяем, существует ли доктор с данным идентификатором
	        if (doctorOptional.isPresent()) {
	            var doctor = doctorOptional.get();
	            
	            // Добавляем пациента к доктору
	            doctor.getPatients().add(patient);
	        } else {
	            // Обработка ситуации, когда доктор не найден
	            // Можно выбросить исключение, записать лог или выполнить другие действия в зависимости от вашей логики
	            throw new EntityNotFoundException("Доктор с идентификатором " + e.getDoctorId() + " не найден");
	        }
	    }
	    
	    // Хешируем пароль пациента
	    patient.setPassword(passwordEncoder.encode(patient.getPassword()));
	    
	    // Сохраняем пациента и возвращаем его идентификатор
	    return repository.save(patient).getId();
	}
	
}
