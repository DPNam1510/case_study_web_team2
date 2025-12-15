
package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;

public class CustomerRepository implements ICustomerRepository {
    private String FIND_BY_USERNAME = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
    private String UPDATE = "UPDATE customer SET name=?, gender=?," +
            " birthday=?, email=?, phone=?, address=? WHERE id=?";

    @Override
    public Customer findByUsername(String username) {
        Customer customer = null;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
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
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setBoolean(2, customer.isGender());
            preparedStatement.setDate(3, Date.valueOf(customer.getBirthday()));
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setInt(7, customer.getId());
            isSuccess = preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

}