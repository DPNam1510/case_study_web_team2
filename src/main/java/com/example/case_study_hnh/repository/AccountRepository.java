//package com.example.case_study_hnh.repository;
//import com.example.case_study_hnh.entity.Account;
//import com.example.case_study_hnh.util.ConnectDB;
//import java.sql.*;
//public class AccountRepository {
//    private static final String LOGIN_SQL =
//            "SELECT username, password, roll FROM account WHERE username=? AND password=?";
//
//    public Account login(String username, String password) {
//        try (Connection connection = ConnectDB.getConnectDB();
//             PreparedStatement ps = connection.prepareStatement(LOGIN_SQL)) {
//
//            ps.setString(1, username);
//            ps.setString(2, password);
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Account(
//                        rs.getString("username"),
//                        rs.getString("password"),
//                        rs.getDate("dateCreate").toLocalDate(),
//                        rs.getString("roll") // 👈 ROLE Ở ĐÂY
//                );
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
