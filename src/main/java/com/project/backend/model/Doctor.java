package com.project.backend.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Doctor
 */
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private String name;

    private String specialization;

    private String phone;

    /**
     * Danh sách khung giờ có sẵn
     */
    @ElementCollection
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "available_time")
    private List<String> availableTimes = new ArrayList<>(); // có thể lưu giờ dưới dạng HH:mm

    public Doctor() {
    }

    public Doctor(String name, String specialization, String phone) {
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
    }

    // Getter và Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void addAvailableTime(String time) {
        this.availableTimes.add(time);
    }
}
