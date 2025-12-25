package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalFormDto;

import java.util.List;

public interface IMedicalFormsService {
    List<MedicalForms> findAll();

    boolean add(MedicalForms medicalForms);

    boolean delete(int id);

    List<MedicalFormDto> findAllHistory();
}
