package com.example.case_study_hnh.entity;
import java.time.LocalDate;

public class Customer {
    private int id;
    private String userName;
    private int customerTypeId;
    private String name;
    private boolean gender;
    private LocalDate birthday;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String role;

    public Customer() {
    }

    public Customer(String userName, int customerTypeId, String name, boolean gender, LocalDate birthday, String email,
                    String phone, String address, String password, String role) {
        this.userName = userName;
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public Customer(int id, String username, int customerTypeId, String name, boolean gender,
                    LocalDate birthday, String email, String phone, String address) {
        this.id = id;
        this.userName = username;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}