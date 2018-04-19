package com.kaishengit.service;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;

import java.util.List;

public interface TicketofficeService {
    List<Ticketoffice> findAll();

    void saveTicketofficeAndSaleAccount(Ticketoffice ticketoffice, SaleAccount saleAccount);
}
