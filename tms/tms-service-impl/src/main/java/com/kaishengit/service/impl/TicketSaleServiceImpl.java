package com.kaishengit.service.impl;

import com.kaishengit.entitys.TicketSale;
import com.kaishengit.entitys.TicketSaleExample;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.TicketStateExample;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.TicketSaleMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.Ticket;

import java.util.Date;
import java.util.List;

@Service
public class TicketSaleServiceImpl implements TicketSaleService {

    @Autowired
    private TicketSaleMapper ticketSaleMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(TicketSale ticketSale) {
        //封装ticketsale对象
        ticketSale.setCeateTime(new Date());
        ticketSale.setState(TicketSale.default_state);

        //查询该身份信息是否已经在此之前买过年票
        TicketSaleExample ticketSaleExample = new TicketSaleExample();
        ticketSaleExample.createCriteria().andCustomerIdEqualTo(ticketSale.getCustomerId());
        List<TicketSale> ticketSaleList = ticketSaleMapper.selectByExample(ticketSaleExample);
        if (ticketSaleList != null){
            throw new ServiceException("该顾客已购买过年票！");
        }else {
            ticketSaleMapper.insertSelective(ticketSale);

            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticketSale.getTicketNum());
            TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);

            ticketState.setState(TicketState.saled_state);
            ticketStateMapper.updateByPrimaryKeySelective(ticketState);
        }

    }
}
