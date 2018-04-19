package com.kaishengit.service.impl;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.mapper.SaleAccountMapper;
import com.kaishengit.service.SaleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleAccountServiceImpl implements SaleAccountService {

    @Autowired
    private SaleAccountMapper saleAccountMapper;

    @Override
    public List<SaleAccount> findAll() {
        return saleAccountMapper.selectByExample(null);
    }
}
