package com.project.backend.controller;

import com.project.backend.models.Prescription;
import com.project.backend.payload.PrescriptionRequest;
import com.project.backend.services.PrescriptionService;
import com.project.backend.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller quản lý Prescription (đơn thuốc)
 */
@RestController
@RequestMapping("/api")
@Validated
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private AuthService authService;

    /**
     * Lưu đơn thuốc mới.
     * Token được truyền dưới dạng PathVariable.
     *
     * @param token token xác thực
     * @param request dữ liệu đơn thuốc
     * @return ResponseEntity với đơn thuốc đã lưu hoặc lỗi
     */
    @PostMapping("/prescriptions/{token}")
    public ResponseEntity<?> savePrescription(
            @PathVariable String token,
            @Valid @RequestBody PrescriptionRequest request) {
        try {
            // 1. Validate token
            if (!authService.isValidToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid token");
            }

            // 2. Lưu prescription thông qua service
            Prescription prescription = prescriptionService.save(request);

            // 3. Trả về prescription đã lưu
            return ResponseEntity.ok(prescription);

        } catch (Exception e) {
            // 4. Trả về lỗi nếu có exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving prescription: " + e.getMessage());
        }
    }
}
