package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Service;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAdminRepository implements IServiceAdminRepository {

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM service ORDER BY id";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Service service = new Service(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("doctor_name")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public List<Service> searchByName(String keyword) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM service WHERE name LIKE ? OR doctor_name LIKE ? ORDER BY id";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("doctor_name")
                    );
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public Service findById(int id) {
        String query = "SELECT * FROM service WHERE id = ?";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Service(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("doctor_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Service service) {
        String query = "INSERT INTO service (name, doctor_name) VALUES (?, ?)";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, service.getName());
            ps.setString(2, service.getDoctorName());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Service service) {
        String query = "UPDATE service SET name = ?, doctor_name = ? WHERE id = ?";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, service.getName());
            ps.setString(2, service.getDoctorName());
            ps.setInt(3, service.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM service WHERE id = ?";

        try (Connection conn = ConnectDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}