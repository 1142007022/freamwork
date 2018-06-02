package com.jiangdongit.service.impl;

import com.jiangdongit.entitys.Account;
import com.jiangdongit.entitys.AccountExample;
import com.jiangdongit.exception.ServiceException;
import com.jiangdongit.mapper.AccountMapper;
import com.jiangdongit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public AccountExample accountExample = new AccountExample();

    @Override
    public void add(Account account) {
        accountExample.createCriteria().andMobileEqualTo(account.getMobile());
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if (accountList != null) {
            throw new ServiceException("该账户已存在，请重试或者直接登录!");
        }else {
            accountMapper.insertSelective(account);
        }
    }
}
