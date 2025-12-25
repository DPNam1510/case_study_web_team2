package com.example.case_study_hnh.service;

import com.example.case_study_hnh.dto.MedicalFormAddDto;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalFormDisplayDto;

import java.util.List;

public interface IMedicalFormsService {
    boolean add(MedicalFormAddDto medicalFormAddDto);

    List<MedicalFormDisplayDto> findByUsername(String username);

    boolean deleteById(int medicalFormId);

}
