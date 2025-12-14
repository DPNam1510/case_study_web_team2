package com.example.case_study_hnh.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicalForms {
    private int id;
    private int customerId;
    private LocalDate dateTime;
    private LocalDateTime appointmentTime;
    private String status;

    public MedicalForms() {}

    public MedicalForms(int id, int customerId, LocalDate dateTime, LocalDateTime appointmentTime, String status) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.appointmentTime = appointmentTime;
        this.status = status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
