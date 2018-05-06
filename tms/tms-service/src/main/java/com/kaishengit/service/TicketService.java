package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.Ticketoffice;

import java.util.List;
import java.util.Map;

public interface TicketService {
    void add(TicketInLog ticketInLog1,String accName);
    List<Ticket> findAll();
    List<Ticket> findByTicketofficeId(Integer id);
    void renew(String ticketNum);

    PageInfo<TicketState> reget(Integer p, Ticketoffice ticketoffice);

    void getAgain(String ticketNum);

    Map<String,Integer> count(Integer id);

    List<Ticket> findTicketByState(String saled_state);
}
