package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalHistoryDto;

import java.util.List;

public interface IMedicalFormsService {
    List<MedicalForms> findAll();

    boolean add(MedicalForms medicalForms);

    boolean delete(int id);

    List<MedicalHistoryDto> findAllHistory();
}
