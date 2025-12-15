package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Service;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements IServiceRepository {
    private final String FIND_ALL = "select * from Service ";
    private final String SEARCH = "select * from Service where name like ? ";

    @Override
    public List<Service> findAll() {
        List<Service> serviceList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctorName = resultSet.getString("doctor_name");
                serviceList.add(new Service(id, name, doctorName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceList;
    }

    @Override
    public List<Service> findByName(String keyWords) {
        List<Service> serviceList = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH);
            preparedStatement.setString(1, "%" + keyWords + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctorName = resultSet.getString("doctor_name");
                serviceList.add(new Service(id, name, doctorName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceList;
    }
}
