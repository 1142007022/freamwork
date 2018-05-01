package com.kaishengit.service.impl;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.*;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.service.TicketService;
import com.kaishengit.service.TicketStateService;
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
    @Autowired
    private TicketStateService ticketStateService;
    @Autowired
    private TicketService ticketService;

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

    @Override
    public PageInfo<TicketState> reget(Integer p, Ticketoffice ticketoffice) {

        PageInfo<TicketState> pageInfo = ticketStateService.findAllTicketStateOfTicketoffice(p);
        List<TicketState> ticketStates = pageInfo.getList();
        List<TicketState> ticketStateList = new ArrayList<>();
        //获取当前登录账户的所拥有的票
        List<Ticket> ticketList = ticketService.findByTicketofficeId(ticketoffice.getId());
        for (int i = 0;i < ticketStates.size();i++) {
            for (int j = 0;j < ticketList.size();j ++){
                if (ticketStates.get(i).getTicketNum().equals(ticketList.get(j).getNum()) && ticketStates.get(i).getState().equals(TicketState.miss_state)){
                    ticketStateList.add(ticketStates.get(i));
                }
            }
        }

        PageInfo<TicketState> ticketStatePageInfo = new PageInfo<>(ticketStateList);

        return ticketStatePageInfo;
    }

    @Override
    public void getAgain(String ticketNum) {
        TicketStateExample ticketStateExample = new TicketStateExample();
        ticketStateExample.createCriteria().andTicketNumEqualTo(ticketNum);
        TicketState ticketState = ticketStateMapper.selectByExample(ticketStateExample).get(0);
        ticketState.setState(TicketState.saled_state);
        ticketStateMapper.updateByPrimaryKeySelective(ticketState);
    }

    @Override
    public Map<String, Integer> count(Integer id) {
        Map<String,Integer> map = new HashMap<>();
        //获取这个售票点所有的年票
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketofficeIdEqualTo(id);
        Long total = ticketMapper.countByExample(ticketExample);
        map.put("total",total.intValue());

        Integer sale_num = ticketMapper.getCountByTicketofficeIdAndState(id,TicketState.saled_state);
        map.put("sale_num",sale_num);

        Integer miss_num = ticketMapper.getCountByTicketofficeIdAndState(id,TicketState.miss_state);
        map.put("miss_num",miss_num);

        Integer over_data_num = ticketMapper.getCountByTicketofficeIdAndState(id,TicketState.overDate_state);
        map.put("over_data_num",over_data_num);

        Integer now_num = total.intValue() - sale_num - miss_num - over_data_num;
        map.put("now_num",now_num);
        return map;
    }

}
