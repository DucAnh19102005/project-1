# Database Schema Design

This document describes the MySQL database schema for the Hospital Management System.

---

## Tables

### Doctor
- doctor_id (INT, PK, AUTO_INCREMENT)
- name (VARCHAR(100), NOT NULL)
- specialization (VARCHAR(100), NOT NULL)
- phone (VARCHAR(20), UNIQUE)

### Patient
- patient_id (INT, PK, AUTO_INCREMENT)
- name (VARCHAR(100), NOT NULL)
- age (INT)
- phone (VARCHAR(20), UNIQUE)

### Appointment
- appointment_id (INT, PK, AUTO_INCREMENT)
- doctor_id (INT, FK → Doctor.doctor_id)
- patient_id (INT, FK → Patient.patient_id)
- appointment_date (DATE)
- status (VARCHAR(50))

### Admin
- admin_id (INT, PK, AUTO_INCREMENT)
- username (VARCHAR(50), UNIQUE, NOT NULL)
- password (VARCHAR(255), NOT NULL)

---

## Relationships
- One Doctor → Many Appointments  
- One Patient → Many Appointments  
- One Admin → Manages Doctors & Patients
