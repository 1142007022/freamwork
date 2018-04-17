package com.kaishengit.service;

import com.kaishengit.entitys.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {

    Account login(String mobile,String password,String loginIp);

    Account addAccount(Account account);

    List<Account> findAll();

    void delById(Integer id);

    Account findById(Integer id);

    void update(Account account);

    List<Account> findAccountWithParam(Map<String, Object> map);
}
