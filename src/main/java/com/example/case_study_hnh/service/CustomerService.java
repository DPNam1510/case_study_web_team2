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
    public Customer findByUserName(String userName) {
        return customerRepository.findByUserName(userName);
    }

    @Override
    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }
}

