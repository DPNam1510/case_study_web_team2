package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.MedicalForms;
import java.util.List;
public interface IMedicalFormsRepository {
    boolean add(MedicalForms medicalForms);
    List<MedicalForms> findByCustomer(int customerId);
    boolean delete(int id);
}
