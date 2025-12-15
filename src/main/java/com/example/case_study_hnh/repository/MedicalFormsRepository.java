package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalFormsRepository implements IMedicalFormsRepository {
    private final String FIND_ALL = "select * from medical_forms";
    private final String INSERT = "INSERT INTO medical_forms(customer_id, date_time, appointment_time, status) VALUES (?, ?, ?, ?)";
    private final String FIND_BY_ID = "SELECT * FROM medical_forms WHERE customer_id = ?";
    private final String DELETE_BY_ID = "DELETE FROM medical_forms WHERE id = ?";

    @Override
    public List<MedicalForms> findAll(){
        List<MedicalForms> medicalFormsList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB();) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                LocalDate dateTime = resultSet.getDate("date_time").toLocalDate();
                LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                String status = resultSet.getString("status");
                MedicalForms medicalForms = new MedicalForms(id,customerId,dateTime,appointmentTime,status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicalFormsList;
    }
    @Override
    public boolean add(MedicalForms medicalForms) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, medicalForms.getCustomerId());
            preparedStatement.setDate(2, Date.valueOf(medicalForms.getDateTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(medicalForms.getAppointmentTime()));
            preparedStatement.setString(4, medicalForms.getStatus());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MedicalForms> findByCustomer(int customerId) {
        List<MedicalForms> medicalFormsList = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDate dateTime = resultSet.getDate("date_time").toLocalDate();
                LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                String status = resultSet.getString("status");
                MedicalForms medicalForms = new MedicalForms(id, customerId, dateTime, appointmentTime, status);
                medicalFormsList.add(medicalForms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicalFormsList;
    }

    @Override
    public boolean delete(int id) {
        boolean isSuccess;
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            isSuccess = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

}
