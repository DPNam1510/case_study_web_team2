package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.repository.CustomerRepository;
import com.example.case_study_hnh.repository.ICustomerRepository;

import java.util.List;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }
}

