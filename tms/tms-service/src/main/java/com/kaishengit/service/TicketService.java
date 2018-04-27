package com.kaishengit.service;

import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketInLog;

import java.util.List;

public interface TicketService {
    void add(TicketInLog ticketInLog1,String accName);
    List<Ticket> findAll();
    List<Ticket> findByTicketofficeId(Integer id);
    void renew(String ticketNum);
}
