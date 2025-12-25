package com.example.case_study_hnh.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicalFormDto {
    private int medicalFormId;
    private String customerUsername;
    private String customerName;
    private LocalDate medicalDate;
    private LocalDateTime appointmentTime;
    private String status;
    private String serviceName;
    private String doctorName;

    public MedicalFormDto() {
    }

    public MedicalFormDto(int medicalFormId, String customerUsername, String customerName,
                          LocalDate medicalDate, LocalDateTime appointmentTime,
                          String status, String serviceName, String doctorName) {
        this.medicalFormId = medicalFormId;
        this.customerUsername = customerUsername;
        this.customerName = customerName;
        this.medicalDate = medicalDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.serviceName = serviceName;
        this.doctorName = doctorName;
    }

    public int getMedicalFormId() {
        return medicalFormId;
    }

    public void setMedicalFormId(int medicalFormId) {
        this.medicalFormId = medicalFormId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getMedicalDate() {
        return medicalDate;
    }

    public void setMedicalDate(LocalDate medicalDate) {
        this.medicalDate = medicalDate;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
