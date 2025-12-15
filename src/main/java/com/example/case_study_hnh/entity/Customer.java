package com.example.case_study_hnh.entity;

import java.time.LocalDate;

public class Customer {
    private int id;
    private String username;
    private int customerTypeId;

    private String name;
    private Boolean gender;      // ✅ đổi sang Boolean để có thể null
    private LocalDate birthday;  // ✅ có thể null
    private String email;
    private String phone;
    private String address;

    private String password; // lấy từ account join qua
    private String role;     // lấy từ account join qua (cột roll)

    public Customer() {}

    public Customer(int id, String username, int customerTypeId, String name, Boolean gender, LocalDate birthday, String email, String phone, String address) {
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

    public Customer(String username, int customerTypeId, String name, Boolean gender,
                    LocalDate birthday, String email, String phone, String address,
                    String password, String role) {
        this.username = username;
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

    public Customer(int id, String username, int customerTypeId, String name, Boolean gender,
                    LocalDate birthday, String email, String phone, String address,
                    String password, String role) {
        this.id = id;
        this.username = username;
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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getCustomerTypeId() { return customerTypeId; }
    public void setCustomerTypeId(int customerTypeId) { this.customerTypeId = customerTypeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }
    // ✅ giữ thêm isGender() để code/JSP cũ không vỡ nếu đang dùng
    public boolean isGender() { return gender != null && gender; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}