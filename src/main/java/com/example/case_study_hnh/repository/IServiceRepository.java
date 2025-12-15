package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.Service;
import java.util.List;
public interface IServiceRepository {
    List<Service> findAll();
    List<Service> findByName(String keyWords);
}
