
package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.time.LocalDate;

public class CustomerRepository implements ICustomerRepository {
    private String FIND_ALL_CUSTOMER = "SELECT * FROM CUSTOMER";
    private String FIND_BY_USERNAME = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
    private String UPDATE = "UPDATE customer SET useName=?, customerTypeId=?, name=?, gender=?," +
            " birthday=?, email=?, phone=?, address=? WHERE id=?";

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CUSTOMER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                int customerTypeId = resultSet.getInt("customerTypeId");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id, userName, customerTypeId, name, gender, birthday, email, phone, address);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                int customerTypeId = resultSet.getInt("customer_type_id");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                customer = new Customer(id, userName, customerTypeId, name, gender, birthday, email, phone, address);
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
            preparedStatement.setString(1, customer.getUserName());
            preparedStatement.setInt(2, customer.getCustomerTypeId());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setBoolean(4, customer.isGender());
            preparedStatement.setDate(5, Date.valueOf(customer.getBirthday()));
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setString(7, customer.getPhone());
            preparedStatement.setString(8, customer.getAddress());
            preparedStatement.setInt(9, customer.getId());
            isSuccess = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

}