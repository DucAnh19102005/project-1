package com.project.backend.services;

import com.project.backend.models.Appointment;
import com.project.backend.models.Doctor;
import com.project.backend.models.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service quản lý thông tin bác sĩ và khung giờ làm việc.
 */
public class DoctorService {

    // Giả sử có danh sách bác sĩ lưu trữ
    private List<Doctor> doctors = new ArrayList<>();

    // Giả sử có danh sách tất cả các appointment
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Lấy danh sách tất cả bác sĩ
     */
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    /**
     * Lấy thông tin bác sĩ theo ID
     */
    public Doctor getDoctorById(int doctorId) {
        return doctors.stream()
                .filter(d -> d.getId() == doctorId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Lấy danh sách tất cả khung giờ trong một ngày
     */
    public List<TimeSlot> getAllTimeSlots() {
        List<TimeSlot> slots = new ArrayList<>();
        LocalTime start = LocalTime.of(8, 0);   // Bắt đầu từ 8:00
        LocalTime end = LocalTime.of(17, 0);    // Kết thúc 17:00
        while (start.isBefore(end)) {
            slots.add(new TimeSlot(start));
            start = start.plusMinutes(30);      // Mỗi slot 30 phút
        }
        return slots;
    }

    /**
     * Lấy danh sách appointment của bác sĩ theo ngày
     */
    public List<Appointment> getAppointmentsByDoctorAndDate(int doctorId, LocalDate date) {
        return appointments.stream()
                .filter(a -> a.getDoctorId() == doctorId && a.getDate().equals(date))
                .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách các khung giờ trống của bác sĩ theo ngày
     */
    public List<TimeSlot> getAvailableTimeSlots(int doctorId, LocalDate date) {
        List<TimeSlot> allSlots = getAllTimeSlots();
        List<Appointment> bookedAppointments = getAppointmentsByDoctorAndDate(doctorId, date);

        // Lọc ra các slot còn trống
        return allSlots.stream()
                .filter(slot -> bookedAppointments.stream().noneMatch(app -> app.getTimeSlot().equals(slot)))
                .collect(Collectors.toList());
    }

    /**
     * Kiểm tra xem bác sĩ có còn slot trống vào thời gian nhất định không
     */
    public boolean isDoctorAvailable(int doctorId, LocalDate date, TimeSlot slot) {
        return getAvailableTimeSlots(doctorId, date).contains(slot);
    }

    /**
     * Thêm bác sĩ (chỉ ví dụ)
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Thêm appointment (chỉ ví dụ)
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
