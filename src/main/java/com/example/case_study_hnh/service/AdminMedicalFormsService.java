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
    public List<AdminMedicalFormsDto> getList() {
        return  adminMedicalFormsRepo.getList();
    }

    @Override
    public List<AdminMedicalFormsDto> getListRejected() {
        return adminMedicalFormsRepo.getListRejected();
    }

    @Override
    public List<AdminMedicalFormsDto> getListPayNotYet() {
        return adminMedicalFormsRepo.getListPayNotYet();
    }

    @Override
    public boolean delete(int id) {
        return adminMedicalFormsRepo.delete(id);
    }

    @Override
    public boolean approve(int formId) {
        int customerId = adminMedicalFormsRepo.getCustomerIdByFormId(formId);
        if (customerId == -1) return false;

        if (!adminMedicalFormsRepo.isCustomerInfoComplete(customerId)) {
            return false;
        }

        return adminMedicalFormsRepo.approve(formId);
    }

    @Override
    public boolean reject(int formId) {
        return adminMedicalFormsRepo.reject(formId);
    }

    @Override
    public List<AdminMedicalFormsDto> searchApprove(String searchName, String searchService) {
        return adminMedicalFormsRepo.searchApprove(searchName,searchService);
    }

    @Override
    public List<AdminMedicalFormsDto> searchPayNotYet(String searchName, String searchService) {
        return adminMedicalFormsRepo.searchPayNotYet(searchName,searchService);
    }

    @Override
    public List<AdminMedicalFormsDto> searchPending(String searchName, String searchService) {
        return adminMedicalFormsRepo.searchPending(searchName,searchService);
    }

    @Override
    public List<AdminMedicalFormsDto> searchReject(String searchName, String searchService) {
        return adminMedicalFormsRepo.searchReject(searchName,searchService);
    }


}
