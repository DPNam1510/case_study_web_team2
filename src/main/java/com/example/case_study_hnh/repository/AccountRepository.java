package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountRepository implements IAccountRepository{

    private final String SELECT_USER = "select * from account where username = ?";
    private final String INSERT = "INSERT INTO account(username, password, date_create, role) VALUES(?,?,?,?)";

    private final String EXISTS_USER =
            "SELECT 1 FROM account WHERE username = ?";


    @Override
    public boolean existUsername(String username) {
        try (Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement ps = connection.prepareStatement(EXISTS_USER);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getByUsername(String username) {
        Account account = null;

        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String usernameGet = resultSet.getString("username");
                String passwordGet = resultSet.getString("password");
                LocalDate dateCreate = resultSet.getObject("date_create", LocalDate.class);
                String role = resultSet.getString("role");
                account = new Account(usernameGet,passwordGet,dateCreate,role);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public boolean register(Account account) {
        try(Connection connection = ConnectDB.getConnectDB()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setObject(3, account.getDateCreate());
            preparedStatement.setString(4, account.getRole());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
