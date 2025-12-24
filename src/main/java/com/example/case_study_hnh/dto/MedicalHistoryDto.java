package com.example.case_study_hnh.dto;

import java.time.LocalDateTime;

public class MedicalHistoryDto {
    private int formId;
    private LocalDateTime appointmentTime;
    private String status;
    private String serviceName;
    private String doctorName;

    public MedicalHistoryDto() {
    }

    public MedicalHistoryDto(int formId, LocalDateTime appointmentTime, String status, String serviceName, String doctorName) {
        this.formId = formId;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.serviceName = serviceName;
        this.doctorName = doctorName;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
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
