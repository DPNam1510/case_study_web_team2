package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private String FIND_USERNAME = "select a.username as username, ct.name as type_customer, c.name, c.gender,c.birthday,c.email,c.phone,c.address FROM customer WHERE a.username = ?";
    private String UPDATE = "UPDATE customer SET customer_type_id = ?, name = ?, gender = ?, " +
            "birthday = ?, email = ?, phone = ?, address = ? " +
            "WHERE username = ?";


    @Override
    public Customer findByUsername(String username) {
        Customer customer = null;
        try( Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerTypeName = resultSet.getString("type_customer");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                customer = new Customer(username, customerTypeName, name, gender, birthday, email, phone, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer) {
        boolean isSuccess;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, customer.getCustomerTypeId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setBoolean(3, customer.getGender());
            preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getAddress());
            preparedStatement.setString(8, customer.getUsername());
            isSuccess = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

}