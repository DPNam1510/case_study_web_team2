package com.example.case_study_hnh.service;
import com.example.case_study_hnh.entity.CustomerType;
import com.example.case_study_hnh.repository.ICustomerTypeRepo;
import com.example.case_study_hnh.repository.CustomerTypeRepo;
import java.util.List;
public class CustomerTypeService implements ICustomerTypeService {
    private final ICustomerTypeRepo customerTypeRepo = new CustomerTypeRepo();
    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepo.findAll();
    }
}
