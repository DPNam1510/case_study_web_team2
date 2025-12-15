package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import java.util.List;

public interface ICustomerAdminRepository {
    List<Customer> findAll();
    List<Customer> searchByName(String keyword);
    Customer findById(int id);
    boolean hasUsedService(int customerId);
    boolean deleteCustomer(int customerId);
}