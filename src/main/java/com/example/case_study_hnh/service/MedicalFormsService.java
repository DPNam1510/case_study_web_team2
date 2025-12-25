package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.repository.MedicalFormsRepository;
import com.example.case_study_hnh.repository.IMedicalFormsRepository;
import com.example.case_study_hnh.dto.MedicalFormDto;

import java.util.List;

public class MedicalFormsService implements IMedicalFormsService {
    private final IMedicalFormsRepository medicalFormsRepository = new MedicalFormsRepository();

    @Override
    public List<MedicalForms> findAll() {
        return medicalFormsRepository.findAll();
    }

    @Override
    public boolean add(MedicalForms medicalForms) {
        return medicalFormsRepository.add(medicalForms);
    }

    @Override
    public boolean delete(int id) {
        return medicalFormsRepository.delete(id);
    }

    @Override
    public List<MedicalFormDto> findAllHistory() {
        return medicalFormsRepository.findAllHistory();
    }

}
