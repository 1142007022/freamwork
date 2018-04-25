package com.kaishengit.service.impl;

import com.kaishengit.entitys.AccountLoginLog;
import com.kaishengit.mapper.AccountLoginLogMapper;
import com.kaishengit.service.AccountLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountLoginLogServiceImpl implements AccountLoginLogService {
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;
    @Override
    public void insertSelective(AccountLoginLog accountLoginLog) {
        accountLoginLogMapper.insertSelective(accountLoginLog);
    }
}
