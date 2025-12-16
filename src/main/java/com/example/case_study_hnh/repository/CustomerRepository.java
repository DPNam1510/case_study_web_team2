package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;
import java.sql.*;
import java.time.LocalDate;

public class CustomerRepository implements ICustomerRepository {
    private String FIND_ALL_CUSTOMER = "SELECT * FROM CUSTOMER";
    private String FIND_BY_USERNAME = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
    private String UPDATE = "UPDATE customer SET useName=?, customerTypeId=?, name=?, gender=?," +
            " birthday=?, email=?, phone=?, address=? WHERE id=?";

    @Override
    public Customer findByUsername(String username) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("customer_type_id"),
                        rs.getString("name"),
                        rs.getBoolean("gender"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setInt(2, customer.getCustomerTypeId());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getAddress());
            preparedStatement.setInt(8, customer.getId());
            isSuccess = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

}