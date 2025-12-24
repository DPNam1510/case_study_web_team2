package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalHistoryDto;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalFormsRepository implements IMedicalFormsRepository {
    private final String FIND_ALL = "SELECT * FROM medical_forms";
    private final String ADD = "INSERT INTO medical_forms(customer_id, date_time, appointment_time, status) " +
                    "VALUES (?, ?, ?, ?)";
    private final String DELETE_FORM_DETAIL = "DELETE FROM forms_detail WHERE forms_id = ?";

    private final String DELETE_MEDICAL_FORM = "DELETE FROM medical_forms WHERE id = ?";
    private final String FIND_HISTORY =
            "SELECT mf.id AS form_id, mf.appointment_time, mf.status, " +
                    "       s.name AS service_name, s.doctor_name " +
                    "FROM medical_forms mf " +
                    "JOIN forms_detail fd ON mf.id = fd.forms_id " +
                    "JOIN service s ON fd.service_id = s.id " +
                    "ORDER BY mf.appointment_time DESC";
    @Override
    public List<MedicalForms> findAll() {
        List<MedicalForms> medicalFormsList = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                LocalDate dateTime = resultSet.getDate("date_time").toLocalDate();
                LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                String status = resultSet.getString("status");
                MedicalForms medicalForms = new MedicalForms(id,customerId,dateTime,appointmentTime,status);
                medicalFormsList.add(medicalForms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medicalFormsList;
    }
    @Override
    public boolean add(MedicalForms medicalForms) {
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD)) {

            preparedStatement.setInt(1, medicalForms.getCustomerId());
            preparedStatement.setDate(2, Date.valueOf(medicalForms.getDateTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(medicalForms.getAppointmentTime()));
            preparedStatement.setString(4, medicalForms.getStatus());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConnectDB();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_FORM_DETAIL)) {
                preparedStatement1.setInt(1, id);
                preparedStatement1.executeUpdate();
            }

            try (PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_MEDICAL_FORM)) {
                preparedStatement2.setInt(1, id);
                preparedStatement2.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {
                ignored.printStackTrace();
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {
                ignored.printStackTrace();
            }
        }
    }
    @Override
    public List<MedicalHistoryDto> findAllHistory() {
        List<MedicalHistoryDto> list = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_HISTORY)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new MedicalHistoryDto(
                        resultSet.getInt("form_id"),
                        resultSet.getTimestamp("appointment_time").toLocalDateTime(),
                        resultSet.getString("status"),
                        resultSet.getString("service_name"),
                        resultSet.getString("doctor_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
