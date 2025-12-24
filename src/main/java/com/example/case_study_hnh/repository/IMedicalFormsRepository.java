package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalHistoryDto;
import java.util.List;
public interface IMedicalFormsRepository {
    List<MedicalForms> findAll();
    boolean add(MedicalForms medicalForms);
    boolean delete(int id);
    List<MedicalHistoryDto> findAllHistory();

}
