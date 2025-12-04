package com.example.case_study_hnh.entity;

import java.time.LocalDate;

public class MedicalForms {
    private int id;
    private int customerId;
    private LocalDate dateTime;

    public MedicalForms() {}

    public MedicalForms(int id, int customerId, LocalDate dateTime) {
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
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
