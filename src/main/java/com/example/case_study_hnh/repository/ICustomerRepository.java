
package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    Customer findByUsername(String username);

    Customer findById(int id);

    boolean update(Customer customer);
}