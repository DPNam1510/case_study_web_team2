package com.example.case_study_hnh.entity;
import java.time.LocalDate;
import java.util.List;

public class Orders {
    private int id;
    private int customerId;
    private int payTypeId;
    private LocalDate dateTime;

    public Orders() {}

    public Orders(int id, int customerId, int payTypeId, LocalDate dateTime) {
        this.id = id;
        this.customerId = customerId;
        this.payTypeId = payTypeId;
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

    public int getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(int payTypeId) {
        this.payTypeId = payTypeId;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

}