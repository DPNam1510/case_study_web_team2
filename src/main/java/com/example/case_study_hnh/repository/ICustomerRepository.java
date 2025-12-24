package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.dto.CustomerDto;

import java.util.List;

public interface ICustomerRepository {
    CustomerDto findByUsername(String username);

    boolean update(Customer customer);
}