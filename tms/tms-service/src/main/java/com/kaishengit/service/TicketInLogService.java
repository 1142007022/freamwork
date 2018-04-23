package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.exception.ServiceException;

import java.util.List;

public interface TicketInLogService {
    TicketInLog add(TicketInLog ticketInLog)throws ServiceException;

    PageInfo<TicketInLog> findAll(Integer p);

    void delById(Integer id);

    TicketInLog findById(Integer id);

    void update(TicketInLog ticketInLog);
}
