package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int id);

    boolean update(Customer customer);
}
