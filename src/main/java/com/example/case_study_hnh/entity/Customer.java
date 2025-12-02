package com.example.case_study_hnh.entity;
import java.time.LocalDate;

public class Customer {
    private int id;
    private String username;
    private int customerTypeId;
    private String name;
    private boolean gender;
    private LocalDate birthday;
    private String email;
    private String phone;
    private String address;

    public Customer() {}

    public Customer(String username, int customerTypeId, String name, boolean gender, LocalDate birthday, String email, String phone, String address) {
        this.username = username;
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Customer(int id, String username, int customerTypeId, String name, boolean gender,
                    LocalDate birthday, String email, String phone, String address) {
        this.id = id;
        this.username = username;
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}