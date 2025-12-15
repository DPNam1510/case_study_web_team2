package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Customer;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAdminRepository implements ICustomerAdminRepository {

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT c.*, a.password, a.roll FROM customer c " +
                "JOIN account a ON c.username = a.username " +
                "WHERE a.roll = 'customer' ORDER BY c.id DESC";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("customer_type_id"),
                        rs.getString("name"),
                        rs.getBoolean("gender"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password"),
                        rs.getString("roll")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> searchByName(String keyword) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT c.*, a.password, a.roll FROM customer c " +
                "JOIN account a ON c.username = a.username " +
                "WHERE a.roll = 'customer' AND (c.name LIKE ? OR c.email LIKE ? OR c.phone LIKE ?) " +
                "ORDER BY c.id DESC";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("customer_type_id"),
                            rs.getString("name"),
                            rs.getBoolean("gender"),
                            rs.getDate("birthday").toLocalDate(),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("password"),
                            rs.getString("roll")
                    );
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findById(int id) {
        String query = "SELECT c.*, a.password, a.roll FROM customer c " +
                "JOIN account a ON c.username = a.username " +
                "WHERE c.id = ?";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
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
                            rs.getString("address"),
                            rs.getString("password"),
                            rs.getString("roll")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasUsedService(int customerId) {
        String query = "SELECT COUNT(*) as count FROM medical_forms WHERE customer_id = ?";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
            conn.setAutoCommit(false);

            // Get username
            String getUsername = "SELECT username FROM customer WHERE id = ?";
            String username = null;
            try (PreparedStatement ps = conn.prepareStatement(getUsername)) {
                ps.setInt(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("username");
                    }
                }
            }

            if (username == null) {
                conn.rollback();
                return false;
            }

            // Delete customer
            String deleteCustomer = "DELETE FROM customer WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteCustomer)) {
                ps.setInt(1, customerId);
                ps.executeUpdate();
            }

            // Delete account
            String deleteAccount = "DELETE FROM account WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteAccount)) {
                ps.setString(1, username);
                ps.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}