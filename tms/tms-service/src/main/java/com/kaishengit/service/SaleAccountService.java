package com.kaishengit.service;

import com.kaishengit.entitys.SaleAccount;

import java.util.List;

public interface SaleAccountService {
    List<SaleAccount> findAll();

    SaleAccount findByTickId(Integer id);

    void update(SaleAccount saleAccount);

    SaleAccount findByMobile(String mobile);
}
