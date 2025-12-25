package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Service;
import com.example.case_study_hnh.repository.ServiceRepository;
import com.example.case_study_hnh.repository.IServiceRepository;

import java.util.List;

public class ServiceService implements IServiceService {
    private IServiceRepository serviceRepository = new ServiceRepository();

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }
    @Override
    public List<Service> findByName(String name,String doctorName) {
        return serviceRepository.findByName(name,doctorName);
    }




}
