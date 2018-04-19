package com.kaishengit.service.impl;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.SaleAccountExample;
import com.kaishengit.mapper.SaleAccountMapper;
import com.kaishengit.service.SaleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleAccountServiceImpl implements SaleAccountService {

    private SaleAccountExample saleAccountExample = new SaleAccountExample();

    @Autowired
    private SaleAccountMapper saleAccountMapper;

    @Override
    public List<SaleAccount> findAll() {
        return saleAccountMapper.selectByExample(null);
    }

    @Override
    public SaleAccount findByTickId(Integer id) {
        saleAccountExample.createCriteria().andTicketofficeIdEqualTo(id);
        return saleAccountMapper.selectByExample(saleAccountExample).get(0);
    }

    @Override
    public void update(SaleAccount saleAccount) {
        saleAccountMapper.updateByPrimaryKeySelective(saleAccount);
    }
}
