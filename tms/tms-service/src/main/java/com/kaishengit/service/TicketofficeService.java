package com.kaishengit.service;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;

import java.util.List;

public interface TicketofficeService {
    List<Ticketoffice> findAll();

    void saveTicketofficeAndSaleAccount(Ticketoffice ticketoffice, SaleAccount saleAccount);

    void delTickAndAccountByTickId(Integer id);

    Ticketoffice findById(Integer id);

    void update(Ticketoffice ticketoffice);
}
