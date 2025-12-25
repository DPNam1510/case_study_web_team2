package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalFormDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public interface IMedicalFormsRepository {
    boolean addMedicalForm(int customerId, int serviceId,
                           LocalDate medicalDate, LocalDateTime appointmentTime);

    List<MedicalFormDto> findAllByUsername(String username);

    boolean deleteById(int medicalFormId);

}
