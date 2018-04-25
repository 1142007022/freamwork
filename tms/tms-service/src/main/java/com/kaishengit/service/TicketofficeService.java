package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;

import java.util.List;

public interface TicketofficeService {
    PageInfo<Ticketoffice> findAll(Integer p);

    List<Ticketoffice> findAll();

    void saveTicketofficeAndSaleAccount(Ticketoffice ticketoffice, SaleAccount saleAccount);

    void delTickAndAccountByTickId(Integer id);

    Ticketoffice findById(Integer id);

    void update(Ticketoffice ticketoffice);

    Ticketoffice findBySaleAccountId(Integer id);
}
