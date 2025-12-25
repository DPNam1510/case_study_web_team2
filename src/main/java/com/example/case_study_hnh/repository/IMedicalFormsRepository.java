package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.dto.MedicalFormDisplayDto;
import com.example.case_study_hnh.dto.MedicalFormAddDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public interface IMedicalFormsRepository {
    boolean add(MedicalFormAddDto medicalFormAddDto);

    List<MedicalFormDisplayDto> findAllByUsername(String username);

    boolean deleteById(int medicalFormId);

}
