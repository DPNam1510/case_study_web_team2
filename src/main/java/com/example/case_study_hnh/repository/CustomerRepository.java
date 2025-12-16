
package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;


import java.sql.*;

public class CustomerRepository implements ICustomerRepository {

    private static final String FIND_BY_USERNAME =
            "SELECT * FROM customer WHERE username = ?";

    private static final String UPDATE =
            "UPDATE customer SET name=?, gender=?, birthday=?, email=?, phone=?, address=? WHERE username=?";

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
    public boolean update(Customer customer) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, customer.getName());
            ps.setBoolean(2, customer.isGender());
            ps.setDate(3, Date.valueOf(customer.getBirthday()));
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getUsername());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
