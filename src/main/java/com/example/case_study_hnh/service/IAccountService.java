package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Account;

public interface IAccountService {
    Account login(String username,String password);
}
