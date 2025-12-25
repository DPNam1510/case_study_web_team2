package com.example.case_study_hnh.dto;

import java.time.LocalDate;

public class CustomerDto {
    private int id;
    private String username;
    private String customerTypeName;
    private String name;
    private Boolean gender;
    private LocalDate birthday;
    private String email;
    private String phone;
    private String address;

    public CustomerDto() {
    }

    public CustomerDto( int customerId,String username, String customerTypeName, String name, Boolean gender,
                       LocalDate birthday, String email, String phone, String address) {
        this.id = customerId;
        this.username = username;
        this.customerTypeName = customerTypeName;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
