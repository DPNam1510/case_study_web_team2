package com.example.case_study_hnh.service;

import com.example.case_study_hnh.entity.Account;
import com.example.case_study_hnh.repository.AccountRepository;
import com.example.case_study_hnh.repository.IAccountRepository;

public class AccountService implements IAccountService{
    private IAccountRepository accountRepository = new AccountRepository();
    @Override
    public Account login(String username,String password) {
        Account account = accountRepository.getByUsername(username);
        if (account == null){
            return null;
        }
        if (!account.getPassword().equals(password)){
            return null;
        }
        return account;
    }
}
//package com.example.case_study_hnh.service;
//import com.example.case_study_hnh.entity.Account;
//import com.example.case_study_hnh.repository.AccountRepository;
//public class AccountService {
//    private final AccountRepository repository = new AccountRepository();
//
//    public Account login(String username, String password) {
//        return repository.login(username, password);
//    }
//}
