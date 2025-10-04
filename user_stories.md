# User Stories

This document contains user stories for **Doctor**, **Patient**, and **Admin** roles.

---

## Doctor

### Story 1
**Title**: View Appointments  
**As a** Doctor  
**I want** to view all my scheduled appointments  
**So that** I can prepare for my patients.  

- **Acceptance Criteria**:
  - System shows a list of all upcoming appointments for the doctor.
  - Each appointment displays patient name, date, and status.
- **Priority**: High  
- **Story Points**: 5  

### Story 2
**Title**: Update Patient Records  
**As a** Doctor  
**I want** to update my patients' medical records  
**So that** their health information stays accurate.  

- **Acceptance Criteria**:
  - Doctor can add/update medical notes.
  - Records are stored in the database.
- **Priority**: Medium  
- **Story Points**: 3  

---

## Patient

### Story 1
**Title**: Book Appointment  
**As a** Patient  
**I want** to book an appointment with a doctor  
**So that** I can get medical consultation.  

- **Acceptance Criteria**:
  - Patient can select a doctor and date from available slots.
  - Appointment is saved in the system.
- **Priority**: High  
- **Story Points**: 8  

### Story 2
**Title**: View Medical History  
**As a** Patient  
**I want** to view my past appointments and diagnoses  
**So that** I can track my health.  

- **Acceptance Criteria**:
  - System shows a list of past appointments with details.
- **Priority**: Medium  
- **Story Points**: 5  

---

## Admin

### Story 1
**Title**: Manage Accounts  
**As an** Admin  
**I want** to create, update, and delete doctor and patient accounts  
**So that** I can maintain the system.  

- **Acceptance Criteria**:
  - Admin can CRUD doctor/patient accounts.
  - Email must be unique, fields validated.
- **Priority**: High  
- **Story Points**: 5  

### Story 2
**Title**: Generate Reports  
**As an** Admin  
**I want** to generate appointment and doctor performance reports  
**So that** I can monitor the system effectively.  

- **Acceptance Criteria**:
  - Report includes total appointments per doctor.
  - Report can be filtered by date range.
- **Priority**: Medium  
- **Story Points**: 3  
