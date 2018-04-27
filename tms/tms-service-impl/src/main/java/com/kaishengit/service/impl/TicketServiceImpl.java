package com.kaishengit.service.impl;

import com.kaishengit.entitys.*;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(TicketInLog ticketInLog1,String accName) {

        List<Ticket> ticketList = new ArrayList<>();

        BigInteger start = new BigInteger(ticketInLog1.getStartNum());
        BigInteger end = new BigInteger(ticketInLog1.getEndNum());

        for(int i = 0;i < ticketInLog1.getTotalNum();i++){
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            Ticket ticket = new Ticket();
            calendar.setTime(date);
            ticket.setInTime(date);
            ticket.setTicketofficeId(Ticket.default_ticketOffice_id);
            ticket.setTicketInLogId(ticketInLog1.getId());
            calendar.add(Calendar.YEAR,1);
            ticket.setContent(accName);
            ticket.setOverDataTime(calendar.getTime());
            ticket.setNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticketList.add(ticket);
            TicketState ticketState = new TicketState();
            ticketState.setState(TicketState.default_state);
            ticketState.setTicketNum(ticket.getNum());
            ticketStateMapper.insertSelective(ticketState);

        }
        ticketMapper.insertCount(ticketList);

    }

    @Override
    public List<Ticket> findAll() {
        return ticketMapper.selectByExample(null);
    }

    @Override
    public List<Ticket> findByTicketofficeId(Integer id) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketofficeIdEqualTo(id);
        return ticketMapper.selectByExample(ticketExample);
    }

    @Override
    public void renew(String ticketNum) {
        //找到要续费的年票
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andNumEqualTo(ticketNum);
        Ticket ticket = ticketMapper.selectByExample(ticketExample).get(0);
        Date date = ticket.getOverDataTime();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,1);
        ticket.setOverDataTime(calendar.getTime());
        ticketMapper.updateByPrimaryKeySelective(ticket);
    }

}
