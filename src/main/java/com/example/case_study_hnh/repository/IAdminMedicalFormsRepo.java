package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.dto.AdminMedicalFormsDto;


import java.util.List;

public interface IAdminMedicalFormsRepo {
    List<AdminMedicalFormsDto> getAll();
    List<AdminMedicalFormsDto> getList();
    List<AdminMedicalFormsDto> getListRejected();
    List<AdminMedicalFormsDto> getListPayNotYet();
    boolean delete(int id);
    boolean approve(int formId);
    boolean reject(int formId);
    boolean isCustomerInfoComplete(int customerId);
    int getCustomerIdByFormId(int formId);
    List<AdminMedicalFormsDto> searchApprove (String searchName, String searchService);
    List<AdminMedicalFormsDto> searchPayNotYet (String searchName, String searchService);
    List<AdminMedicalFormsDto> searchPending (String searchName, String searchService);
    List<AdminMedicalFormsDto> searchReject (String searchName, String searchService);
}
