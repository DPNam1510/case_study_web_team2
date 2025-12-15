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
}
