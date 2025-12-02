package com.example.case_study_hnh.entity;
import java.time.LocalDateTime;

public class OrdersDetail {
    private int id;
    private int ordersId;
    private int supplementsId;
    private int quantity;
    private LocalDateTime dateTime;

    public OrdersDetail() {}

    public OrdersDetail(int id, int ordersId, int supplementsId, int quantity, LocalDateTime dateTime) {
        this.id = id;
        this.ordersId = ordersId;
        this.supplementsId = supplementsId;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getSupplementsId() {
        return supplementsId;
    }

    public void setSupplementsId(int supplementsId) {
        this.supplementsId = supplementsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}