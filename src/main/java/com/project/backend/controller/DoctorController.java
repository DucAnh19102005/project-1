package com.project.backend.controller;

import com.project.backend.models.Doctor;
import com.project.backend.models.TimeSlot;
import com.project.backend.services.AuthService;
import com.project.backend.services.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller quản lý Doctor
 */
@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AuthService authService;

    /**
     * Lấy danh sách tất cả bác sĩ
     */
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    /**
     * Lấy thông tin bác sĩ theo ID
     */
    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<?> getDoctorById(@PathVariable int doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }

    /**
     * Thêm bác sĩ mới
     */
    @PostMapping("/doctors")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    /**
     * Lấy danh sách khung giờ trống của bác sĩ theo ngày, kiểm tra token và role
     *
     * @param doctorId ID bác sĩ
     * @param date ngày cần kiểm tra (yyyy-MM-dd)
     * @param token token xác thực
     * @return danh sách khung giờ trống hoặc lỗi
     */
    @GetMapping("/doctors/{doctorId}/availability/{date}/{token}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable int doctorId,
            @PathVariable String date,
            @PathVariable String token,
            @RequestParam(required = false) String role) {

        // 1. Validate token
        if (!authService.isValidToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // 2. Kiểm tra role nếu cần (ví dụ: chỉ admin và patient mới xem được)
        if (role != null && !authService.isAuthorized(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient permissions");
        }

        try {
            LocalDate localDate = LocalDate.parse(date);

            // 3. Lấy danh sách khung giờ trống từ service
            List<TimeSlot> availableSlots = doctorService.getAvailableTimeSlots(doctorId, localDate);

            return ResponseEntity.ok(availableSlots);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving availability: " + e.getMessage());
        }
    }
}
