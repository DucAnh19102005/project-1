package com.project.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Liên kết với Doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // ✅ Liên kết với Patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // ✅ Thời gian hẹn khám (phải có và phải ở tương lai)
    @NotNull(message = "Appointment date cannot be null")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;

    // ✅ Ghi chú thêm (có thể để trống)
    private String notes;

    // ✅ Constructor rỗng (bắt buộc cho JPA)
    public Appointment() {}

    // ✅ Constructor đầy đủ
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentDate, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
    }

    // ✅ Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
