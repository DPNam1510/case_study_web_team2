
package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();

    Customer findByUserName(String userName);

    boolean update(Customer customer);
}