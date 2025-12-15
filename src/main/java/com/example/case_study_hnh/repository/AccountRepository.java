package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;

public class AccountRepository implements IAccountRepository {

    private static final String SELECT_BY_USERNAME =
            "SELECT username, password, date_create, role FROM account WHERE username = ?";

    private static final String CHECK_USERNAME =
            "SELECT 1 FROM account WHERE username = ?";

    private static final String INSERT_ACCOUNT =
            "INSERT INTO account(username, password, date_create, role) VALUES(?, ?, ?, ?)";

    private static final String INSERT_CUSTOMER_EMPTY =
            "INSERT INTO customer(username, customer_type_id, name, gender, birthday, email, phone, address) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Account getByUsername(String username) {
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERNAME)) {

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));

                Date d = rs.getDate("date_create");
                account.setDateCreate(d != null ? d.toLocalDate() : null);

                account.setRole(rs.getString("role"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existUsername(String username) {
        try (Connection connection = ConnectDB.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(CHECK_USERNAME)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean register(Account account) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnectDB();
            conn.setAutoCommit(false);


            try (PreparedStatement psAcc = conn.prepareStatement(INSERT_ACCOUNT)) {
                psAcc.setString(1, account.getUsername());
                psAcc.setString(2, account.getPassword());

                LocalDate dc = account.getDateCreate() != null ? account.getDateCreate() : LocalDate.now();
                psAcc.setDate(3, Date.valueOf(dc));

                psAcc.setString(4, account.getRole());
                psAcc.executeUpdate();
            }


            try (PreparedStatement psCus = conn.prepareStatement(INSERT_CUSTOMER_EMPTY)) {
                psCus.setString(1, account.getUsername());
                psCus.setInt(2, 4);

                psCus.setNull(3, Types.VARCHAR);
                psCus.setNull(4, Types.BOOLEAN);
                psCus.setNull(5, Types.DATE);
                psCus.setNull(6, Types.VARCHAR);
                psCus.setNull(7, Types.VARCHAR);
                psCus.setNull(8, Types.VARCHAR);

                psCus.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return false;
    }
}
