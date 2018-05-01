package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketSale;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.TicketStateExample;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketSaleMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TicketSaleServiceImpl implements TicketSaleService {

    @Autowired
    private TicketSaleMapper ticketSaleMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(TicketSale ticketSale) {
        //封装ticketsale对象
        ticketSale.setCeateTime(new Date());
        ticketSale.setState(TicketSale.default_state);

        //查询该身份信息是否已经在此之前买过年票
        TicketSale sale = ticketSaleMapper.findByCustomerId(ticketSale.getCustomerId());
        if (sale != null){
            throw new ServiceException("该顾客已购买过年票！");
        }else {
            ticketSaleMapper.insertSelective(ticketSale);

            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticketSale.getTicketNum());
            TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);

            ticketState.setState(TicketState.saled_state);
            ticketStateMapper.updateByPrimaryKeySelective(ticketState);

            //更新年票的过期时间
            com.kaishengit.entitys.Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketSale.getTicketNum()));
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,1);
            ticket.setOverDataTime(calendar.getTime());
            ticketMapper.updateByPrimaryKeySelective(ticket);

        }

    }

    @Override
    public PageInfo<TicketSale> findAll(Integer p) {
        PageHelper.startPage(p,10);
        List<TicketSale> ticketSaleList = ticketSaleMapper.selectByExample(null);
        return new PageInfo<>(ticketSaleList);
    }
}
