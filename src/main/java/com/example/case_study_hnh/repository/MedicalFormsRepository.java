package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.MedicalForms;
import com.example.case_study_hnh.dto.MedicalFormDto;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalFormsRepository implements IMedicalFormsRepository {
    private static final String INSERT_FORM = "INSERT INTO medical_forms(id, customer_id, date_time, appointment_time, status) " +
                    "VALUES (?, ?, ?, ?, 'Pending')";

    private static final String INSERT_DETAIL = "INSERT INTO forms_detail(id, forms_id, service_id) VALUES (?, ?, ?)";

    private static final String FIND_ALL = "SELECT mf.id AS medical_form_id, c.username AS customer_username, c.name AS customer_name, " +
                    "mf.date_time AS medical_date, mf.appointment_time AS appointment_time, mf.status AS status, " +
                    "s.name AS service_name, s.doctor_name AS doctor_name " +
                    "FROM customer c " +
                    "JOIN medical_forms mf ON mf.customer_id = c.id " +
                    "JOIN forms_detail fd ON fd.forms_id = mf.id " +
                    "JOIN service s ON s.id = fd.service_id " +
                    "WHERE c.username = ?";

    private static final String DELETE_DETAIL = "DELETE FROM forms_detail WHERE forms_id = ?";

    private static final String DELETE_FORM = "DELETE FROM medical_forms WHERE id = ?";

    @Override
    public boolean addMedicalForm(int customerId, int serviceId,
                                  LocalDate medicalDate, LocalDateTime appointmentTime) {

        Connection connection = null;
        try {
            connection = ConnectDB.getConnectDB();
            connection.setAutoCommit(false);

            int formId = (int) (System.currentTimeMillis() / 1000);
            int detailId = formId + 1;

            try (PreparedStatement psForm = connection.prepareStatement(INSERT_FORM);
                 PreparedStatement psDetail = connection.prepareStatement(INSERT_DETAIL)) {

                psForm.setInt(1, formId);
                psForm.setInt(2, customerId);
                psForm.setDate(3, Date.valueOf(medicalDate));
                psForm.setTimestamp(4, Timestamp.valueOf(appointmentTime));
                psForm.executeUpdate();

                psDetail.setInt(1, detailId);
                psDetail.setInt(2, formId);
                psDetail.setInt(3, serviceId);
                psDetail.executeUpdate();

                connection.commit(); // ✅ OK
                return true;
            }

        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<MedicalFormDto> findAllByUsername(String username) {
        List<MedicalFormDto> list = new ArrayList<>();

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new MedicalFormDto(
                        rs.getInt("medical_form_id"),
                        rs.getString("customer_username"),
                        rs.getString("customer_name"),
                        rs.getDate("medical_date").toLocalDate(),
                        rs.getTimestamp("appointment_time").toLocalDateTime(),
                        rs.getString("status"),
                        rs.getString("service_name"),
                        rs.getString("doctor_name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean deleteById(int medicalFormId) {

        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
            conn.setAutoCommit(false); // 🔥 TRANSACTION

            try (PreparedStatement psDetail = conn.prepareStatement(DELETE_DETAIL);
                 PreparedStatement psForm = conn.prepareStatement(DELETE_FORM)) {

                psDetail.setInt(1, medicalFormId);
                psDetail.executeUpdate();

                psForm.setInt(1, medicalFormId);
                int row = psForm.executeUpdate();

                conn.commit();
                return row > 0;
            }

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
