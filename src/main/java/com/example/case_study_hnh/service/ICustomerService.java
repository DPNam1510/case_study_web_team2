package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto findByUsername(String username);
    boolean update(Customer customer);

}
