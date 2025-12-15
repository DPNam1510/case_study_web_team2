package com.example.case_study_hnh.repository;

import com.example.case_study_hnh.entity.Account;

import java.util.List;

public interface IAccountRepository {
    Account getByUsername(String username);
}
