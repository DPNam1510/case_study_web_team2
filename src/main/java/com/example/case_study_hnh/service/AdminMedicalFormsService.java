package com.example.case_study_hnh.service;

import com.example.case_study_hnh.dto.AdminMedicalFormsDto;
import com.example.case_study_hnh.repository.AdminMedicalFormsRepo;
import com.example.case_study_hnh.repository.IAdminMedicalFormsRepo;

import java.util.List;

public class AdminMedicalFormsService implements IAdminMedicalFormsService{
    private IAdminMedicalFormsRepo adminMedicalFormsRepo = new AdminMedicalFormsRepo();
    @Override
    public List<AdminMedicalFormsDto> getAll() {
        return adminMedicalFormsRepo.getAll();
    }

    @Override
    public boolean delete(int id) {
        return adminMedicalFormsRepo.delete(id);
    }

    @Override
    public void updateStatus(int formId, String status) {
        adminMedicalFormsRepo.updateStatus(formId,status);
    }

}
