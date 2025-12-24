package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.dto.CustomerDto;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;

public class CustomerRepository implements ICustomerRepository {
    private String FIND_USERNAME = "select a.username as username,ct.name as type_customer, c.name, c.gender,c.birthday,c.email,c.phone,c.address\n" +
            "from customer c " +
            "join customer_type ct on ct.id = c.customer_type_id " +
            "join account a on a.username = c.username " +
            "where a.username = ?;";
    private String UPDATE = "UPDATE customer SET customer_type_id = ?, name = ?, gender = ?, " +
            "birthday = ?, email = ?, phone = ?, address = ? " +
            "WHERE username = ?";


    @Override
    public CustomerDto findByUsername(String username) {
        CustomerDto customerDto = null;
        try( Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                String customerTypeName = resultSet.getString("type_customer");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                Date sqlDate = resultSet.getDate("birthday");
                LocalDate birthday = sqlDate != null ? sqlDate.toLocalDate() : null;
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                customerDto = new CustomerDto(username,customerTypeName,name,gender,birthday,email,phone,address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDto;
    }

    @Override
    public boolean update(Customer customer) {
        boolean isSuccess;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, customer.getCustomerTypeId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setBoolean(3, customer.getGender());
            if (customer.getBirthday() != null) {
                preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
            } else {
                preparedStatement.setNull(4, Types.DATE);
            }

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