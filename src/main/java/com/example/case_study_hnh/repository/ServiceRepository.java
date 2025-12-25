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
    private final String SEARCH_NAME = "select * from service where name like ?";
    private final String SEARCH_DOCTOR_NAME = "select * from service where doctor_name like ?";
    private final String SEARCH_ALL = "select * from service where name like ? and doctor_name like ?";


    @Override
    public List<Service> findAll() {
        List<Service> serviceList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
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

    @Override
    public List<Service> findByName(String name, String doctorName) {
        List<Service> serviceList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectDB.getConnectDB()) {
            if (doctorName.equals("")){
                preparedStatement = connection.prepareStatement(SEARCH_NAME);
                preparedStatement.setString(1, "%" + name + "%");
            } else if (name.equals("")) {
                preparedStatement = connection.prepareStatement(SEARCH_DOCTOR_NAME);
                preparedStatement.setString(1, "%" + doctorName + "%");
            }else {
                preparedStatement = connection.prepareStatement(SEARCH_ALL);
                preparedStatement.setString(1, "%" + name + "%");
                preparedStatement.setString(1, "%" + doctorName + "%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String searchName = resultSet.getString("name");
                String searchDoctorName = resultSet.getString("doctor_name");
                serviceList.add(new Service(id, searchName,searchDoctorName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceList;
    }
}
