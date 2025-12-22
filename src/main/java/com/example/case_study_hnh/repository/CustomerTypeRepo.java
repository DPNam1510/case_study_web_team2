package com.example.case_study_hnh.repository;
import com.example.case_study_hnh.entity.CustomerType;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import  java.util.List;
public class CustomerTypeRepo implements ICustomerTypeRepo {
    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypeList = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer_type");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                CustomerType customerType = new CustomerType(id, name);
                customerTypeList.add(customerType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerTypeList;
    }
}
