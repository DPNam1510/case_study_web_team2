package com.example.case_study_hnh.dto;

public class AdminMedicalFormsDto {
    private int formsId;
    private String customerName;
    private String serviceName;
    private String doctorName;
    private String status;

    public AdminMedicalFormsDto() {
    }

    public AdminMedicalFormsDto(int id, String customerName, String serviceName, String doctorName) {
        this.formsId = id;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.doctorName = doctorName;
    }

    public AdminMedicalFormsDto(int formsId, String customerName, String serviceName, String doctorName, String status) {
        this.formsId = formsId;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.doctorName = doctorName;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFormsId() {
        return formsId;
    }

    public void setFormsId(int formsId) {
        this.formsId = formsId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
