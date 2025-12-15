package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Orders;
import com.example.case_study_hnh.repository.OrdersRepository;
import com.example.case_study_hnh.repository.IOrdersRepository;

import java.util.List;


public class OrdersService implements IOrdersService {
    private final IOrdersRepository ordersRepository = new OrdersRepository();

    @Override
    public List<Orders> findByCustomer(int customerId) {
        return ordersRepository.findByCustomer(customerId);
    }

    @Override
    public Orders findById(int Id) {
        return ordersRepository.findById(Id);
    }

    @Override
    public boolean add(Orders orders) {
        return ordersRepository.add(orders);
    }

    @Override
    public boolean delete(int Id) {
        return ordersRepository.delete(Id);
    }
}
