package com.example.case_study_hnh.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicalFormAddDto {
    private int customerId;
    private int serviceId;
    private LocalDate medicalDate;
    private LocalDateTime appointmentTime;

    public MedicalFormAddDto() {
    }

    public MedicalFormAddDto(int customerId, int serviceId, LocalDate medicalDate, LocalDateTime appointmentTime) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.medicalDate = medicalDate;
        this.appointmentTime = appointmentTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
}
