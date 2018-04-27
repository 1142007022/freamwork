package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketSale;

import java.util.List;

public interface TicketSaleService {
    void add(TicketSale ticketSale);
    PageInfo<TicketSale> findAll(Integer p);
}
