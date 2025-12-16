package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findByUsername(String username);

    boolean update(Customer customer);

}
