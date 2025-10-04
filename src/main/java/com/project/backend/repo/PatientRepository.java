package com.example.healthcare.repository;

import com.example.healthcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE p.email = :input OR p.phone = :input")
    Optional<Patient> findByEmailOrPhone(String input);
}
