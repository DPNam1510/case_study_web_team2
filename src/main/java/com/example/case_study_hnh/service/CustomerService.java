package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.repository.CustomerRepository;
import com.example.case_study_hnh.repository.ICustomerRepository;


public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository = new CustomerRepository();

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }
}


