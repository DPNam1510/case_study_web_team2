package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Service;
import java.util.List;

public interface IServiceAdminRepository {
    List<Service> findAll();
    List<Service> searchByName(String keyword);
    Service findById(int id);
    boolean create(Service service);
    boolean update(Service service);
    boolean delete(int id);
}