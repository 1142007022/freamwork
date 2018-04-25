package com.kaishengit.service.impl;

import com.kaishengit.entitys.Account;
import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(TicketInLog ticketInLog1) {

        List<Ticket> ticketList = new ArrayList<>();

        BigInteger start = new BigInteger(ticketInLog1.getStartNum());
        BigInteger end = new BigInteger(ticketInLog1.getEndNum());

        for(int i = 0;i < ticketInLog1.getTotalNum();i++){
            Ticket ticket = new Ticket();
            ticket.setInTime(new Date());
            ticket.setTicketofficeId(Ticket.default_ticketOffice_id);
            ticket.setTicketInLogId(ticketInLog1.getId());
            ticket.setContent("新增入库");
            ticket.setNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticketList.add(ticket);
            TicketState ticketState = new TicketState();
            ticketState.setState(TicketState.default_state);
            ticketState.setTicketNum(ticket.getNum());
            ticketStateMapper.insertSelective(ticketState);

        }
        ticketMapper.insertCount(ticketList);

    }
}
