package com.example.healthcare.controller;

import com.example.healthcare.entity.Prescription;
import com.example.healthcare.service.PrescriptionService;
import com.example.healthcare.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createPrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription) {

        if (!jwtTokenUtil.validateToken(token.substring(7))) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        try {
            Prescription saved = prescriptionService.save(prescription);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save prescription: " + e.getMessage());
        }
    }
}
