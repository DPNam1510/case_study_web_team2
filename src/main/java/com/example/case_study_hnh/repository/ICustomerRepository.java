package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;

public interface ICustomerRepository {
    Customer findByUsername(String username);

    boolean update(Customer customer);
}
