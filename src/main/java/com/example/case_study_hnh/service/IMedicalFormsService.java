package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.MedicalForms;

import java.util.List;

public interface IMedicalFormsService {
    List<MedicalForms> findAll();
    boolean add(MedicalForms medicalForms);

    List<MedicalForms> findByCustomer(int customerId);

    boolean delete(int id);
}
