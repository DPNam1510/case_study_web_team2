package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.dto.MedicalFormAddDto;
import com.example.case_study_hnh.dto.MedicalFormDisplayDto;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalFormsRepository implements IMedicalFormsRepository {
    private static final String INSERT_FORM = "INSERT INTO medical_forms(customer_id, date_time, appointment_time, status) " +
            "VALUES (?, ?, ?, 'Pending')";

    private static final String INSERT_DETAIL = "INSERT INTO forms_detail(forms_id, service_id, date_time) VALUES (?, ?, ?)";

    private static final String FIND_ALL = "SELECT mf.id AS medical_form_id, c.username AS customer_username, c.name AS customer_name, " +
            "mf.date_time AS medical_date, mf.appointment_time AS appointment_time, mf.status AS status, " +
            "GROUP_CONCAT(s.name SEPARATOR ', ') AS service_name, " +
            "GROUP_CONCAT(s.doctor_name SEPARATOR ', ') AS doctor_name " +
            "FROM customer c " +
            "JOIN medical_forms mf ON mf.customer_id = c.id " +
            "JOIN forms_detail fd ON fd.forms_id = mf.id " +
            "JOIN service s ON s.id = fd.service_id " +
            "WHERE c.username = ? " +
            "GROUP BY mf.id, c.username, c.name, mf.date_time, mf.appointment_time, mf.status " +
            "ORDER BY mf.date_time DESC";

    private static final String DELETE_DETAIL = "DELETE FROM forms_detail WHERE forms_id = ?";

    private static final String DELETE_FORM = "DELETE FROM medical_forms WHERE id = ?";

    @Override
    public boolean add(MedicalFormAddDto medicalFormAddDto) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            connection.setAutoCommit(false);
            PreparedStatement psForm = connection.prepareStatement(INSERT_FORM, Statement.RETURN_GENERATED_KEYS);
            psForm.setInt(1, medicalFormAddDto.getCustomerId());
            psForm.setDate(2, Date.valueOf(medicalFormAddDto.getMedicalDate()));
            psForm.setTimestamp(3, Timestamp.valueOf(medicalFormAddDto.getAppointmentTime()));
            psForm.executeUpdate();
            ResultSet rsForm = psForm.getGeneratedKeys();
            if (!rsForm.next()) {
                connection.rollback();
                return false;
            }
            int formId = rsForm.getInt(1);
            PreparedStatement psDetail = connection.prepareStatement(INSERT_DETAIL);
            psDetail.setInt(1, formId);
            psDetail.setInt(2, medicalFormAddDto.getServiceId());
            psDetail.setTimestamp(3, Timestamp.valueOf(medicalFormAddDto.getAppointmentTime()));
            psDetail.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<MedicalFormDisplayDto> findAllByUsername(String username) {
        List<MedicalFormDisplayDto> displayDtoList = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int medicalFormId = resultSet.getInt("medical_form_id");
                String customerUsername = resultSet.getString("customer_username");
                String customerName = resultSet.getString("customer_name");
                LocalDate medicalDate = resultSet.getDate("medical_date").toLocalDate();
                LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                String status = resultSet.getString("status");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                MedicalFormDisplayDto DisplayDto = new MedicalFormDisplayDto(medicalFormId, customerUsername, customerName
                        , medicalDate, appointmentTime, status, serviceName, doctorName);
                displayDtoList.add(DisplayDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return displayDtoList;
    }

    @Override
    public boolean deleteById(int medicalFormId) {
        boolean isSuccess = false;
        try (Connection connection = ConnectDB.getConnectDB()) {
            connection.setAutoCommit(false);
            try (PreparedStatement psDetail = connection.prepareStatement(DELETE_DETAIL);
                 PreparedStatement psForm = connection.prepareStatement(DELETE_FORM)) {
                //Xóa form_detail
                psDetail.setInt(1, medicalFormId);
                psDetail.executeUpdate();
                //Xóa medical_form
                psForm.setInt(1, medicalFormId);
                int row = psForm.executeUpdate();
                connection.commit();
                isSuccess = row > 0;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
