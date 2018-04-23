package com.kaishengit.service.impl;

import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.TicketStateExample;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class TicketStateServiceImpl implements TicketStateService {

    @Autowired
    private TicketStateMapper ticketStateMapper;

    @Override
    public Map<String, Object> getCount() {
        Map<String,Object> count = new HashMap<>();
        int ruku =  ticketStateMapper.countByState(TicketState.default_state);

        int xiafa = ticketStateMapper.countByState(TicketState.gived_state);

        int guashi = ticketStateMapper.countByState(TicketState.miss_state);

        int xiaoshou = ticketStateMapper.countByState(TicketState.saled_state);

        int all = (int)ticketStateMapper.countByExample(null);

        count.put("ruku",ruku);
        count.put("xiafa",xiafa);
        count.put("guashi",guashi);
        count.put("xiaoshou",xiaoshou);
        count.put("all",all);

        return count;
    }
}
