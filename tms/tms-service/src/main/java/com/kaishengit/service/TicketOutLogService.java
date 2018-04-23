package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketOutLog;

import java.util.List;

public interface TicketOutLogService {
    void add(TicketOutLog ticketOutLog);

    PageInfo<TicketOutLog> findAll(Integer p);

    void del(Integer id);

    TicketOutLog findById(Integer id);

    void update(TicketOutLog ticketOutLog);
}
