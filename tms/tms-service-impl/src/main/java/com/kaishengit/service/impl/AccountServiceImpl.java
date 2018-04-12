package com.kaishengit.service.impl;


import com.kaishengit.entitys.Account;
import com.kaishengit.entitys.AccountExample;
import com.kaishengit.entitys.AccountLoginLog;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.AccountLoginLogMapper;
import com.kaishengit.mapper.AccountMapper;
import com.kaishengit.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    public Account login(String mobile, String password, String loginIp) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andMobileEqualTo(mobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && accountList.size() != 0){
            Account account = accountList.get(0);
            if(account.getPassword().equals(DigestUtils.md5Hex(password))){
                if (account.getStatus().equals(Account.Status_Normal)){
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(loginIp);
                    accountLoginLogMapper.insertSelective(accountLoginLog);
                    return account;
                }else {
                    throw new ServiceException("�ʺ�״̬�쳣");
                }
            }else{
                throw new ServiceException("�ʺŻ����������");
            }
        }else{
            throw new ServiceException("�ʺŲ�����");
        }
    }
}
