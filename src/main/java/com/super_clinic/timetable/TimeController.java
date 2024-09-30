package com.super_clinic.timetable;

import com.super_clinic.dto.AppointmentDto;
import com.super_clinic.dto.DoctorDto;
import com.super_clinic.dto.ServiceDto;
import com.super_clinic.service.impl.AppointmentServiceImpl;
import com.super_clinic.service.impl.DoctorServiceImpl;
import com.super_clinic.service.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/clinic")
public class TimeController {

    private final DoctorServiceImpl doctorService;
    private final AppointmentServiceImpl appointmentService;
    private final FreeTimeSlots freeTimeSlots;
    private final ServiceServiceImpl serviceService;

    @Autowired
    public TimeController(DoctorServiceImpl doctorService, AppointmentServiceImpl appointmentService, FreeTimeSlots freeTimeSlots, ServiceServiceImpl serviceService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.freeTimeSlots = freeTimeSlots;
        this.serviceService = serviceService;
    }

    @GetMapping("/free_space/doctor/{doctor_id}/service/{service_id}")
    public List<FreeTimeSlots.TimeSlot> FreeSpaceFor(@PathVariable("doctor_id") Long doctor_id,
                                                     @PathVariable("service_id") Long service_id,
                                                     @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        DoctorDto doctorDto = doctorService.findById(doctor_id).orElse(null);
        ServiceDto serviceDto = serviceService.findById(service_id).orElse(null);

        if (doctorDto == null || serviceDto == null) {
            throw new IllegalArgumentException("Doctor or Service not found");
        }

        List<AppointmentDto> appointmentDtos = doctorDto.getAppointmentDtos().stream()
                .filter(appointment -> appointment.getStart().toLocalDate().equals(date))
                .collect(Collectors.toList());

        LocalDateTime workStart = date.atTime(LocalTime.of(9, 0));
        LocalDateTime workEnd = date.atTime(LocalTime.of(18, 0));

        return freeTimeSlots.findFreeSlots(appointmentDtos, (int) serviceDto.getDuration().toMinutes(), 2, workStart, workEnd);
    }
}
