package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Service;

import java.util.List;

public interface IServiceService {
    List<Service> findAll();
    List<Service> findByName(String name,String doctorName);
}
