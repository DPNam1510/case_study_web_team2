package com.example.case_study_hnh.entity;

import java.time.LocalDate;

public class DietarySupplements {
    private int id;
    private String name;
    private LocalDate productionDate;
    private LocalDate expDate;
    private String description;
    private double price;

    public DietarySupplements() {}

    public DietarySupplements(String name, LocalDate productionDate, LocalDate expDate, String description, double price) {
        this.name = name;
        this.productionDate = productionDate;
        this.expDate = expDate;
        this.description = description;
        this.price = price;
    }

    public DietarySupplements(int id, String name, LocalDate productionDate, LocalDate expDate,
                              String description, double price) {
        this.id = id; this.name = name; this.productionDate = productionDate;
        this.expDate = expDate; this.description = description; this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
