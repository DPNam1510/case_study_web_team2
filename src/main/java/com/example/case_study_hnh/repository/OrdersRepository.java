package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Orders;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository implements IOrdersRepository {
    private final String FIND_BY_CUSTOMER_ID = "SELECT * FROM orders WHERE customer_id = ? ORDER BY order_date";
    private final String INSERT = "INSERT INTO orders(customer_id, pay_type_id, date_time) VALUES (?, ?, ?)";
    private final String FIND_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private final String DELETE = "DELETE FROM orders WHERE id = ?";

    @Override
    public boolean add(Orders orders) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, orders.getCustomerId());
            preparedStatement.setInt(2, orders.getPayTypeId());
            preparedStatement.setDate(3, Date.valueOf(orders.getDateTime()));
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Orders> findByCustomer(int customerId) {
        List<Orders> ordersList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CUSTOMER_ID);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int payTypeId = resultSet.getInt("pay_type_id");
                LocalDate dateTime = resultSet.getDate("date_Time").toLocalDate();
                Orders orders = new Orders(Id, customerId, payTypeId, dateTime);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordersList;
    }

    @Override
    public Orders findById(int id) {
        Orders orders = null;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int Id = resultSet.getInt("id");
                int payTypeId = resultSet.getInt("pay_type_id");
                LocalDate dateTime = resultSet.getDate("date_Time").toLocalDate();
                orders = new Orders(Id, id, payTypeId, dateTime);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public boolean delete(int id) {
        boolean isSuccess;
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            isSuccess = preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
}
