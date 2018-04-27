package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketState;

import java.util.List;
import java.util.Map;

public interface TicketStateService {
    Map<String,Object> getCount();

    PageInfo<TicketState> findAll(Integer p);
    List<TicketState> findAll();

    void miss(String ticketNum);

    PageInfo<TicketState> findAllTicketStateOfTicketoffice(Integer p);
}
