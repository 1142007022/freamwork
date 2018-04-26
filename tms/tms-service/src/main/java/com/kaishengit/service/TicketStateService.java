package com.kaishengit.service;

import com.kaishengit.entitys.TicketState;

import java.util.List;
import java.util.Map;

public interface TicketStateService {
    Map<String,Object> getCount();

    List<TicketState> findAll();
}
