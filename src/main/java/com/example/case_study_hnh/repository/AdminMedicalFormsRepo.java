package com.example.case_study_hnh.repository;


import com.example.case_study_hnh.dto.AdminMedicalFormsDto;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminMedicalFormsRepo implements IAdminMedicalFormsRepo{

    private final String REGISTERED_CHECK = "SELECT mf.id as forms_id, c.name as customer_name, s.name as service_name, s.doctor_name, mf.status " +
            "FROM medical_forms mf " +
            "JOIN customer c ON mf.customer_id = c.id " +
            "JOIN forms_detail fd ON mf.id = fd.forms_id " +
            "JOIN service s ON fd.service_id = s.id " +
            "WHERE c.name IS NOT NULL AND c.gender IS NOT NULL AND c.birthday IS NOT NULL " +
            "AND c.email IS NOT NULL AND c.phone IS NOT NULL AND c.address IS NOT NULL " +
            "ORDER BY mf.id;";

    private final String UPDATE_STATUS = "UPDATE medical_forms SET status = ? WHERE id = ?";
    private final String DELETE_BY_ID = "delete  from medical_forms where id = ?";
    @Override
    public List<AdminMedicalFormsDto> getAll() {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        Connection connection = ConnectDB.getConnectDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTERED_CHECK);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("s.doctor_name");
                String status = resultSet.getString("mf.status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    @Override
    public boolean delete(int id) {
        boolean isSuccess;
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,id);
            isSuccess = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }


    @Override
    public void updateStatus(int formId, String status) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS);
            ps.setString(1, status);
            ps.setInt(2, formId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
