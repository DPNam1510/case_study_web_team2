package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findByCustomer(int customerId);

    boolean add(Orders orders);

    Orders findById(int id);

    boolean delete(int id);
}
