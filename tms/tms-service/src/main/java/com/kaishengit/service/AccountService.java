package com.kaishengit.service;

import com.kaishengit.entitys.Account;

import java.util.List;

public interface AccountService {

    Account login(String mobile,String password,String loginIp);

    void addAccount(Account account);

    List<Account> findAll();
}
