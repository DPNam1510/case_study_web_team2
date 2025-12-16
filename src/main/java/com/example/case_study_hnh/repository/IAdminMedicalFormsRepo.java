package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.dto.AdminMedicalFormsDto;


import java.util.List;

public interface IAdminMedicalFormsRepo {
    List<AdminMedicalFormsDto> getAll();
    boolean delete(int id);
    void updateStatus(int formId, String status);
}
