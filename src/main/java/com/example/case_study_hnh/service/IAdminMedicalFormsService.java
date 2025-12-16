package com.example.case_study_hnh.service;

import com.example.case_study_hnh.dto.AdminMedicalFormsDto;

import java.util.List;

public interface IAdminMedicalFormsService {
    List<AdminMedicalFormsDto> getAll();
    boolean delete(int id);
    void updateStatus(int formId, String status);
}
