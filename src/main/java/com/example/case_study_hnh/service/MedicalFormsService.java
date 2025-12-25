package com.example.case_study_hnh.service;

import com.example.case_study_hnh.repository.MedicalFormsRepository;
import com.example.case_study_hnh.repository.IMedicalFormsRepository;
import com.example.case_study_hnh.dto.MedicalFormDisplayDto;
import com.example.case_study_hnh.dto.MedicalFormAddDto;

import java.util.List;

public class MedicalFormsService implements IMedicalFormsService {
    private final IMedicalFormsRepository medicalFormsRepository = new MedicalFormsRepository();
    @Override
    public boolean add(MedicalFormAddDto medicalFormAddDto){
        return medicalFormsRepository.add(medicalFormAddDto);
    }
    @Override
    public List<MedicalFormDisplayDto> findByUsername(String username) {
        return medicalFormsRepository.findAllByUsername(username);
    }
    @Override
    public boolean deleteById(int medicalFormId) {
        return medicalFormsRepository.deleteById(medicalFormId);
    }

}
