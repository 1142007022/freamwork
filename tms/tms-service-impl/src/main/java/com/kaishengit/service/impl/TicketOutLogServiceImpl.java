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

        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        BigInteger start = new BigInteger(ticketOutLog.getStartNum());
        BigInteger end = new BigInteger(ticketOutLog.getEndNum());
        BigInteger totalNum = end.subtract(start);
        ticketOutLog.setTotalNum(totalNum.intValue() + 1);
        BigDecimal totalPrice = ticketOutLog.getPrice().multiply(new BigDecimal(ticketOutLog.getTotalNum()));
        ticketOutLog.setToatlPrice(totalPrice);
        ticketOutLog.setCreateTime(new Date());
        ticketOutLog.setPayType(TicketOutLog.default_pay_status);
        ticketOutLog.setOutAccountName(account.getAccName());

        List<Ticket> tickets = new ArrayList<>();

        //判断当前的票号内时候有已经下发过的年票
        for (int i = 0;i < ticketOutLog.getTotalNum();i++) {
            Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketOutLog.getStartNum())+i);
            System.out.println("-----"+ticketOutLog.getStartNum() + i);
                if (ticket.getTicketofficeId() != 0){
                    throw new ServiceException("当前票段中有年票已下发");
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
        //根据id查找到要操作的下发记录
        TicketOutLog ticketOutLog = ticketOutLogMapper.selectByPrimaryKey(id);
        //查找对应的售票点
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
        //封装当前的发放记录对象
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        BigInteger start = new BigInteger(ticketOutLog.getStartNum());
        BigInteger end = new BigInteger(ticketOutLog.getEndNum());
        BigInteger totalNum = end.subtract(start);
        ticketOutLog.setTotalNum(totalNum.intValue() + 1);
        BigDecimal totalPrice = ticketOutLog.getPrice().multiply(new BigDecimal(ticketOutLog.getTotalNum()));
        ticketOutLog.setToatlPrice(totalPrice);
        ticketOutLog.setCreateTime(new Date());
        ticketOutLog.setPayType(TicketOutLog.default_pay_status);
        ticketOutLog.setOutAccountName(account.getAccName());

        // 处理售票点  首先判断目前选中的售票点和之前保存的售票点是不是一个
        TicketOutLog ticketOutLogOld = ticketOutLogMapper.selectByPrimaryKey(ticketOutLog.getId());
        if (ticketOutLog.getTicketofficeId().equals(ticketOutLogOld.getTicketofficeId())){
            //跟新前后选择的是同一个售票点 判断票的数量是增加了还是减少了
            Ticketoffice ticketoffice = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
            if (ticketOutLogOld.getTotalNum()>ticketOutLog.getTotalNum()){
                ticketoffice.setTicketNum(ticketoffice.getTicketNum() - (ticketOutLogOld.getTotalNum() - ticketOutLog.getTotalNum()));
            }else{
                ticketoffice.setTicketNum(ticketoffice.getTicketNum() + (ticketOutLog.getTotalNum() - ticketOutLogOld.getTotalNum()));
            }
            ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);
        }else{
            //现在选择的和上次下发的不是同一个售票点
            Ticketoffice ticketofficeNew = ticketofficeMapper.selectByPrimaryKey(ticketOutLog.getTicketofficeId());
            Ticketoffice ticketofficeOld = ticketofficeMapper.selectByPrimaryKey(ticketOutLogOld.getTicketofficeId());
            ticketofficeOld.setTicketNum(ticketofficeOld.getTicketNum() - ticketOutLogOld.getTotalNum());
            ticketofficeNew.setTicketNum(ticketofficeNew.getTicketNum() + ticketOutLog.getTotalNum());
            ticketofficeMapper.updateByPrimaryKeySelective(ticketofficeNew);
            ticketofficeMapper.updateByPrimaryKeySelective(ticketofficeOld);
        }


        //处理年票票信息
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

        ticketOutLogOld = null;
        ticketOutLogMapper.updateByPrimaryKeySelective(ticketOutLog);

    }
}
