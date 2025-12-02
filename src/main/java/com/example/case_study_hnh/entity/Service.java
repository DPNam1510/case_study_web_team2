package com.example.case_study_hnh.entity;

public class Service {
    private int id;
    private String name;
    private String doctorName;

    public Service() {}

    public Service(String name, String doctorName) {
        this.name = name;
        this.doctorName = doctorName;
    }

    public Service(int id, String name, String doctorName) {
        this.id = id; this.name = name; this.doctorName = doctorName;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
