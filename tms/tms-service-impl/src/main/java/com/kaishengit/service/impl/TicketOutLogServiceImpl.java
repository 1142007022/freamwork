package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.service.TicketOutLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketOutLogServiceImpl implements TicketOutLogService{
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketOutLogMapper ticketOutLogMapper;
    @Autowired
    private TicketofficeMapper ticketofficeMapper;

    @Autowired
    private TicketInLogMapper ticketInLogMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void add(TicketOutLog ticketOutLog) {


        BigInteger start = new BigInteger(ticketOutLog.getStartNum());
        BigInteger end = new BigInteger(ticketOutLog.getEndNum());
        BigInteger totalNum = end.subtract(start);
        ticketOutLog.setTotalNum(totalNum.intValue() + 1);
        BigDecimal totalPrice = ticketOutLog.getPrice().multiply(new BigDecimal(ticketOutLog.getTotalNum()));
        ticketOutLog.setToatlPrice(totalPrice);
        ticketOutLog.setCreateTime(new Date());
        ticketOutLog.setPayType(TicketOutLog.default_pay_status);

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0;i < ticketOutLog.getTotalNum();i++) {
            Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketOutLog.getStartNum())+i);
            System.out.println("-----"+ticketOutLog.getStartNum() + i);
                if (ticket.getTicketofficeId() != 0){
                    throw new ServiceException("有票号已下发");
                }
            tickets.add(ticket);
        }

        ticketOutLogMapper.insertSelective(ticketOutLog);

        int ticketInLogId = tickets.get(0).getTicketInLogId();

        for (Ticket ticket:tickets) {
            ticket.setTicketofficeId(ticketOutLog.getTicketofficeId());
            ticket.setOutTime(new Date());
            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticket.getNum());
            List<TicketState> ticketStates = ticketStateMapper.selectByExample(ticketStateExample);
            TicketState ticketState = ticketStates.get(0);
            ticketState.setState(TicketState.gived_state);
            ticketStateMapper.updateByPrimaryKeySelective(ticketState);
            ticketMapper.updateByPrimaryKeySelective(ticket);
        }

        Ticketoffice ticketoffice = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
        ticketoffice.setTicketNum(ticketoffice.getTicketNum() + ticketOutLog.getTotalNum());
        ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);

        TicketInLog ticketInLog = ticketInLogMapper.selectByPrimaryKey(ticketInLogId);
        ticketInLog.setState("已下发");
        ticketInLogMapper.updateByPrimaryKeySelective(ticketInLog);

    }

    @Override
    public PageInfo<TicketOutLog> findAll(Integer p) {
        PageHelper.startPage(p,10);
        List<TicketOutLog> ticketOutLogsList = ticketOutLogMapper.selectByExample(null);
        return new PageInfo<>(ticketOutLogsList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void del(Integer id) {
        TicketOutLog ticketOutLog = ticketOutLogMapper.selectByPrimaryKey(id);
        Ticketoffice ticketoffice = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
        ticketoffice.setTicketNum(ticketoffice.getTicketNum() - ticketOutLog.getTotalNum());
        ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);
        ticketOutLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TicketOutLog findById(Integer id) {
        return ticketOutLogMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(TicketOutLog ticketOutLog) {
        BigInteger start = new BigInteger(ticketOutLog.getStartNum());
        BigInteger end = new BigInteger(ticketOutLog.getEndNum());
        BigInteger totalNum = end.subtract(start);
        ticketOutLog.setTotalNum(totalNum.intValue() + 1);
        BigDecimal totalPrice = ticketOutLog.getPrice().multiply(new BigDecimal(ticketOutLog.getTotalNum()));
        ticketOutLog.setToatlPrice(totalPrice);
        ticketOutLog.setCreateTime(new Date());
        ticketOutLog.setPayType(TicketOutLog.default_pay_status);

        TicketOutLog ticketOutLogOld = ticketOutLogMapper.selectByPrimaryKey(ticketOutLog.getId());
        if (ticketOutLog.getTicketofficeId().equals(ticketOutLogOld.getTicketofficeId())){
            Ticketoffice ticketoffice = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
            if (ticketOutLogOld.getTotalNum()>ticketOutLog.getTotalNum()){
                ticketoffice.setTicketNum(ticketoffice.getTicketNum() - (ticketOutLogOld.getTotalNum() - ticketOutLog.getTotalNum()));
            }else{
                ticketoffice.setTicketNum(ticketoffice.getTicketNum() + (ticketOutLog.getTotalNum() - ticketOutLogOld.getTotalNum()));
            }
            ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);
        }else{
            Ticketoffice ticketofficeNew = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
            Ticketoffice ticketofficeOld = ticketofficeMapper.selectByPrimaryKey(ticketOutLogOld.getTicketofficeId());
            ticketofficeOld.setTicketNum(ticketofficeOld.getTicketNum() - ticketOutLogOld.getTotalNum());
            ticketofficeNew.setTicketNum(ticketofficeNew.getTicketNum() + ticketOutLog.getTotalNum());
            ticketofficeMapper.updateByPrimaryKeySelective(ticketofficeNew);
            ticketofficeMapper.updateByPrimaryKeySelective(ticketofficeOld);
        }


        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0;i < ticketOutLogOld.getTotalNum();i++) {
            Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketOutLog.getStartNum())+i);
            tickets.add(ticket);
        }

        for (Ticket ticket:tickets) {
            ticket.setTicketofficeId(Ticket.default_ticketOffice_id);
            ticket.setOutTime(null);
            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticket.getNum());
            TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);
            ticketState.setState(TicketState.default_state);
            ticketStateMapper.updateByPrimaryKeySelective(ticketState);
            ticketMapper.updateByPrimaryKeySelective(ticket);
        }

        List<Ticket> updateTicketList = new ArrayList<>();
        for (int i = 0;i < ticketOutLog.getTotalNum();i++) {
            Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketOutLog.getStartNum())+i);
            System.out.println("-----"+ticketOutLog.getStartNum() + i);
            updateTicketList.add(ticket);
        }


        for (Ticket ticket:updateTicketList) {
            ticket.setTicketofficeId(ticketOutLog.getTicketofficeId());
            ticket.setOutTime(new Date());
            ticketMapper.updateByPrimaryKeySelective(ticket);
            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticket.getNum());
            TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);
            ticketState.setState(TicketState.gived_state);
            ticketStateMapper.updateByPrimaryKeySelective(ticketState);
        }

        ticketOutLogMapper.updateByPrimaryKeySelective(ticketOutLog);

    }

    @Override
    public List<TicketOutLog> findAll() {
        return ticketOutLogMapper.selectByExample(null);
    }

    @Override
    public void updatePayType(TicketOutLog ticketOutLog) {
        TicketOutLog ticketOutLog1 = ticketOutLogMapper.selectByPrimaryKey(ticketOutLog.getId());
        ticketOutLog1.setPayType(ticketOutLog.getPayType());
        ticketOutLogMapper.updateByPrimaryKeySelective(ticketOutLog1);
    }
}
