package com.example.case_study_hnh.entity;

import java.time.LocalDateTime;

public class FormsDetail {
    private int id;
    private int formsId;
    private int serviceId;
    private String diagnosisTerminology;
    private String prescription;
    private double prescriptionPrice;
    private LocalDateTime dateTime;

    public FormsDetail() {}

    public FormsDetail(int id, int formsId, int serviceId, String diagnosisTerminology,
                       String prescription, double prescriptionPrice, LocalDateTime dateTime) {
        this.id = id;
        this.formsId = formsId;
        this.serviceId = serviceId;
        this.diagnosisTerminology = diagnosisTerminology;
        this.prescription = prescription;
        this.prescriptionPrice = prescriptionPrice;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormsId() {
        return formsId;
    }

    public void setFormsId(int formsId) {
        this.formsId = formsId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDiagnosisTerminology() {
        return diagnosisTerminology;
    }

    public void setDiagnosisTerminology(String diagnosisTerminology) {
        this.diagnosisTerminology = diagnosisTerminology;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public double getPrescriptionPrice() {
        return prescriptionPrice;
    }

    public void setPrescriptionPrice(double prescriptionPrice) {
        this.prescriptionPrice = prescriptionPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
