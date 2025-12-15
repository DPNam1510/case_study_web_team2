package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.Orders;
import java.util.List;
public interface IOrdersRepository {
    List<Orders> findByCustomer(int customerId);
    boolean add(Orders orders);

}
