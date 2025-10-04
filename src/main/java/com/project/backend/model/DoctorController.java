package com.project.backend.controllers;

import com.project.backend.models.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    private List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor d : doctors) {
            if (d.getDoctorId() == id) return d;
        }
        return null;
    }

    public boolean updateDoctor(int id, Doctor updatedDoctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId() == id) {
                doctors.set(i, updatedDoctor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteDoctor(int id) {
        return doctors.removeIf(d -> d.getDoctorId() == id);
    }
}
