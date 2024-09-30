package com.super_clinic.timetable;

import com.super_clinic.dto.AppointmentDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FreeTimeSlots {

    public static class TimeSlot {
        public LocalDateTime start;
        public LocalDateTime end;

        public TimeSlot(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " - " + end;
        }
    }

    public static List<TimeSlot> findFreeSlots(List<AppointmentDto> appointments, int appointmentDuration, int gapMinutes, LocalDateTime workStart, LocalDateTime workEnd) {
        List<TimeSlot> freeSlots = new ArrayList<>();

        // Сортируем приёмы по времени начала
        appointments.sort((a1, a2) -> a1.getStart().compareTo(a2.getStart()));

        // Проверяем свободное время до первого приёма
        if (appointments.isEmpty() || workStart.plusMinutes(appointmentDuration).isBefore(appointments.get(0).getStart())) {
            freeSlots.add(new TimeSlot(workStart, appointments.isEmpty() ? workEnd : appointments.get(0).getStart()));
        }

        // Проверяем промежутки между приёмами
        for (int i = 0; i < appointments.size() - 1; i++) {
            LocalDateTime endCurrent = appointments.get(i).getEnd().plusMinutes(gapMinutes);
            LocalDateTime startNext = appointments.get(i + 1).getStart();

            if (endCurrent.plusMinutes(appointmentDuration).isBefore(startNext)) {
                freeSlots.add(new TimeSlot(endCurrent, startNext));
            }
        }

        // Проверяем свободное время после последнего приёма
        LocalDateTime endLast = appointments.get(appointments.size() - 1).getEnd().plusMinutes(gapMinutes);
        if (endLast.plusMinutes(appointmentDuration).isBefore(workEnd)) {
            freeSlots.add(new TimeSlot(endLast, workEnd));
        }

        return freeSlots;
    }
}
