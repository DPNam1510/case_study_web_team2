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

    private final String REGISTERED_CHECK =
            "SELECT mf.id AS forms_id, " +
                    "       c.id AS customer_id, " +
                    "       c.name AS customer_name, " +
                    "       s.name AS service_name, " +
                    "       s.doctor_name AS doctor_name, " +
                    "       mf.status AS status " +
                    "FROM medical_forms mf " +
                    "JOIN customer c ON mf.customer_id = c.id " +
                    "JOIN forms_detail fd ON mf.id = fd.forms_id " +
                    "JOIN service s ON fd.service_id = s.id " +
                    "WHERE mf.status = 'Pending' " +
                    "ORDER BY mf.id;";

    private final String LIST_APPROVE = "SELECT mf.id AS forms_id, " +
            "c.name AS customer_name, " +
            "s.name AS service_name, " +
            "s.doctor_name AS doctor_name, " +
            "mf.status AS status " +
            "FROM medical_forms mf " +
            "JOIN customer c ON mf.customer_id = c.id " +
            "JOIN forms_detail fd ON mf.id = fd.forms_id " +
            "JOIN service s ON fd.service_id = s.id " +
            "WHERE mf.status = 'Completed' " +
            "ORDER BY mf.id; ";
    private final String REJECTED = "select mf.id as forms_id,c.name as customer_name,s.name as service_name,s.doctor_name ,mf.status as status " +
            "from medical_forms mf " +
            "join customer c on mf.customer_id = c.id " +
            "join forms_detail fd on mf.id = fd.forms_id " +
            "join service s on fd.service_id = s.id " +
            "where status = 'Reject' " +
            "order by mf.id;";
    private final String UPDATE_STATUS = "update medical_forms mf set mf.status = ? " +
            "where mf.id = ? " +
            "and mf.status = ? " +
            "and exists (select 1 from customer c " +
            "where mf.customer_id = c.id and " +
            "c.username is not null and " +
            "c.customer_type_id is not null and " +
            "c.name is not null and " +
            "c.gender is not null and " +
            "c.birthday is not null and " +
            "c.email is not null and " +
            "c.phone is not null and " +
            "c.address is not null)" +
            ";";
    private final String REJECT = "update medical_forms set status = 'Reject' " +
            "where id = ? " +
            "and status = 'Pending'";
    private final String DELETE_BY_ID = "delete from medical_forms where id = ?";

    private final String SEARCH_NAME_SERVICE = "select mf.id as forms_id,c.name as customer_name,s.name as service_name,s.doctor_name ,mf.status as status " +
            "from medical_forms mf " +
            "join customer c on mf.customer_id = c.id " +
            "join forms_detail fd on mf.id = fd.forms_id " +
            "join service s on fd.service_id = s.id " +
            "where status = ? and c.name like ? and s.name like ? " +
            "order by mf.id;";
    private final String SEARCH_NAME = "select mf.id as forms_id,c.name as customer_name,s.name as service_name,s.doctor_name ,mf.status as status " +
            "from medical_forms mf " +
            "join customer c on mf.customer_id = c.id " +
            "join forms_detail fd on mf.id = fd.forms_id " +
            "join service s on fd.service_id = s.id " +
            "where status = ? and c.name like ? " +
            "order by mf.id;";
    private final String SEARCH_SERVICE = "select mf.id as forms_id,c.name as customer_name,s.name as service_name,s.doctor_name ,mf.status as status " +
            "from medical_forms mf " +
            "join customer c on mf.customer_id = c.id " +
            "join forms_detail fd on mf.id = fd.forms_id " +
            "join service s on fd.service_id = s.id " +
            "where status = ? and s.name like ? " +
            "order by mf.id;";
    @Override
    public List<AdminMedicalFormsDto> getAll() {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTERED_CHECK);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    @Override
    public List<AdminMedicalFormsDto> getList() {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();

        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_APPROVE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    @Override
    public List<AdminMedicalFormsDto> getListRejected() {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REJECTED);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
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
    public boolean approve(int formId) {
        boolean isSuccess;
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS);
            preparedStatement.setString(1,"Completed");
            preparedStatement.setInt(2,formId);
            preparedStatement.setString(3,"Pending");
            isSuccess = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

    @Override
    public boolean reject(int formId) {
        boolean isSuccess;
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REJECT);
            preparedStatement.setInt(1,formId);
            isSuccess = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
    public int getCustomerIdByFormId(int formId) {
        String sql = "SELECT customer_id FROM medical_forms WHERE id = ?";
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, formId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("customer_id");
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AdminMedicalFormsDto> searchApprove(String searchName, String searchService) {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        PreparedStatement  preparedStatement = null;
        try(Connection connection = ConnectDB.getConnectDB()) {
            if (searchService.equals("")){
                preparedStatement = connection.prepareStatement(SEARCH_NAME);
                preparedStatement.setString(1,"completed");
                preparedStatement.setString(2,"%"+searchName+"%");
            } else if (searchName.equals("")) {
                preparedStatement = connection.prepareStatement(SEARCH_SERVICE);
                preparedStatement.setString(1,"completed");
                preparedStatement.setString(2,"%"+searchService+"%");
            }else {
                preparedStatement = connection.prepareStatement(SEARCH_NAME_SERVICE);
                preparedStatement.setString(1,"completed");
                preparedStatement.setString(2,"%"+searchName+"%");
                preparedStatement.setString(3,"%"+searchService+"%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    @Override
    public List<AdminMedicalFormsDto> searchPending(String searchName, String searchService) {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        PreparedStatement  preparedStatement = null;
        try(Connection connection = ConnectDB.getConnectDB()) {
            if (searchService.equals("")){
                preparedStatement = connection.prepareStatement(SEARCH_NAME);
                preparedStatement.setString(1,"pending");
                preparedStatement.setString(2,"%"+searchName+"%");
            } else if (searchName.equals("")) {
                preparedStatement = connection.prepareStatement(SEARCH_SERVICE);
                preparedStatement.setString(1,"pending");
                preparedStatement.setString(2,"%"+searchService+"%");
            }else {
                preparedStatement = connection.prepareStatement(SEARCH_NAME_SERVICE);
                preparedStatement.setString(1,"pending");
                preparedStatement.setString(2,"%"+searchName+"%");
                preparedStatement.setString(3,"%"+searchService+"%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    @Override
    public List<AdminMedicalFormsDto> searchReject(String searchName, String searchService) {
        List<AdminMedicalFormsDto> adminMedicalFormsDtoList = new ArrayList<>();
        PreparedStatement  preparedStatement = null;
        try(Connection connection = ConnectDB.getConnectDB()) {
            if (searchService.equals("")){
                preparedStatement = connection.prepareStatement(SEARCH_NAME);
                preparedStatement.setString(1,"reject");
                preparedStatement.setString(2,"%"+searchName+"%");
            } else if (searchName.equals("")) {
                preparedStatement = connection.prepareStatement(SEARCH_SERVICE);
                preparedStatement.setString(1,"reject");
                preparedStatement.setString(2,"%"+searchService+"%");
            }else {
                preparedStatement = connection.prepareStatement(SEARCH_NAME_SERVICE);
                preparedStatement.setString(1,"reject");
                preparedStatement.setString(2,"%"+searchName+"%");
                preparedStatement.setString(3,"%"+searchService+"%");
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("forms_id");
                String customerName = resultSet.getString("customer_name");
                String serviceName = resultSet.getString("service_name");
                String doctorName = resultSet.getString("doctor_name");
                String status = resultSet.getString("status");
                AdminMedicalFormsDto adminMedicalFormsDto = new AdminMedicalFormsDto(id,customerName,serviceName,doctorName,status);
                adminMedicalFormsDtoList.add(adminMedicalFormsDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminMedicalFormsDtoList;
    }

    public boolean isCustomerInfoComplete(int customerId) {
        String sql = "SELECT 1 FROM customer c " +
                "WHERE c.id = ? " +
                "AND c.username IS NOT NULL " +
                "AND c.customer_type_id IS NOT NULL " +
                "AND c.name IS NOT NULL " +
                "AND c.gender IS NOT NULL " +
                "AND c.birthday IS NOT NULL " +
                "AND c.email IS NOT NULL " +
                "AND c.phone IS NOT NULL " +
                "AND c.address IS NOT NULL";
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
