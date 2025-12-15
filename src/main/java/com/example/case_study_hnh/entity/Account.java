package com.example.case_study_hnh.entity;

import java.time.LocalDate;

public class Account {
    private String username;
    private String password;
    private LocalDate dateCreate;
    private String role;

    public Account() {
    }

    public Account(String username, String password, LocalDate dateCreate, String role) {
        this.username = username;
        this.password = password;
        this.dateCreate = dateCreate;
        this.role = role;
    }

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.dateCreate = LocalDate.now();
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}