package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.TicketStateExample;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public PageInfo<TicketState> findAll(Integer p) {

        PageHelper.startPage(p,10);
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andStateEqualTo(TicketState.saled_state);
        List<TicketState> ticketStates = ticketStateMapper.selectByExample(ticketStateExample);
        return new PageInfo<>(ticketStates);
    }

    @Override
    public List<TicketState> findAll() {
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andStateEqualTo(TicketState.gived_state);
        return ticketStateMapper.selectByExample(null);
    }

    @Override
    public void miss(String ticketNum) {
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andTicketNumEqualTo(ticketNum);
        TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);
        ticketState.setState(TicketState.miss_state);
        ticketStateMapper.updateByPrimaryKeySelective(ticketState);
    }

    @Override
    public PageInfo<TicketState> findAllTicketStateOfTicketoffice(Integer p) {
        PageHelper.startPage(p,10);
        List<String> lists = new ArrayList<>();
        lists.add(TicketState.saled_state);
        lists.add(TicketState.miss_state);
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andStateIn(lists);
        List<TicketState> ticketStates = ticketStateMapper.selectByExample(ticketStateExample);
        return new PageInfo<>(ticketStates);
    }

    @Override
    public TicketState findByNum(String num) {
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andTicketNumEqualTo(num);
        return ticketStateMapper.selectByExample(ticketStateExample).get(0);
    }
}
