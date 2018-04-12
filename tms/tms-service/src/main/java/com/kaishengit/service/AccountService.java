package com.kaishengit.service;

import com.kaishengit.entitys.Account;

public interface AccountService {

    Account login(String mobile,String password,String loginIp);

}
