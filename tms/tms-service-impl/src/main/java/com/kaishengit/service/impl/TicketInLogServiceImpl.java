package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.TicketInLogMapper;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketStateMapper;
import com.kaishengit.mapper.TicketofficeMapper;
import com.kaishengit.service.TicketInLogService;
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
public class TicketInLogServiceImpl implements TicketInLogService {


    @Autowired
    private TicketInLogMapper ticketInLogMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketofficeMapper ticketofficeMapper;
    @Autowired
    private TicketStateMapper ticketStateMapper;
    private TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
    private TicketofficeServiceImpl ticketofficeServiceImpl = new TicketofficeServiceImpl();

    @Override
    public TicketInLog add(TicketInLog ticketInLog) {


        ticketInLog.setState(TicketInLog.default_state);
        ticketInLog.setCreateTime(new Date());
        BigInteger start = new BigInteger(ticketInLog.getStartNum());
        BigInteger end = new BigInteger(ticketInLog.getEndNum());
        BigInteger total = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInLog.setTotalNum(total.intValue());
        for (int i = 0; i < ticketInLog.getTotalNum(); i++) {
            Ticket ticket = ticketMapper.findByNum(Integer.parseInt(ticketInLog.getStartNum()) + i);
            if (ticket != null) {
                throw new ServiceException("��Ʊ���Ѵ��ڣ�");
            }
        }

        ticketInLogMapper.insert(ticketInLog);

        return ticketInLog;
    }

    @Override
    public PageInfo<TicketInLog> findAll(Integer p) {
        List<TicketInLog> ticketInLogList = ticketInLogMapper.selectByExample(null);
        for (int i = 0;i < ticketInLogList.size();i++){
            //����ÿ������¼ȥ������������¼��������Ʊ
            TicketExample ticketExample = new TicketExample();
            ticketExample.createCriteria().andTicketInLogIdEqualTo(ticketInLogList.get(i).getId());
            List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
            //�ж���Щ��Ʊ�����Ƿ����Ѿ��·���
            int total = 0;
            for (int j = 0;j<ticketList.size();j++){
                total = total + ticketList.get(j).getTicketofficeId();
            }
            if (total == 0){
                ticketInLogList.get(i).setState(TicketInLog.default_state);
            }else{
                ticketInLogList.get(i).setState(TicketInLog.gived_state);
            }
            ticketInLogMapper.updateByPrimaryKeySelective(ticketInLogList.get(i));
        }

        PageHelper.startPage(p,10);
        List<TicketInLog> ticketInLogList1 = ticketInLogMapper.selectByExample(null);
        return new PageInfo<>(ticketInLogList1);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delById(Integer id) {
        TicketInLog ticketInLog = ticketInLogMapper.selectByPrimaryKey(id);
        Integer num = ticketInLog.getTotalNum();

        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInLogIdEqualTo(ticketInLog.getId());
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        for (int i = 0;i < ticketList.size();i++) {
            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketStateExample.createCriteria().andTicketNumEqualTo(ticketList.get(i).getNum());
            ticketStateMapper.deleteByExample(ticketStateExample);
        }
        ticketMapper.deleteByExample(ticketExample);
        ticketInLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TicketInLog findById(Integer id) {
        return ticketInLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TicketInLog ticketInLog) {

        List<Ticket> ticketList = new ArrayList<>();


        TicketInLog ticketInLogOld = ticketInLogMapper.selectByPrimaryKey(ticketInLog.getId());
        BigInteger start = new BigInteger(ticketInLog.getStartNum());
        BigInteger end = new BigInteger(ticketInLog.getEndNum());
        BigInteger total = end.subtract(start).add(new BigInteger(String.valueOf(1)));

        ticketInLog.setTotalNum(total.intValue());


        TicketExample ticketExample = new TicketExample();
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        for (int i = 0;i < tickets.size();i++){
            TicketStateExample ticketStateExample = new TicketStateExample();
            ticketExample.createCriteria().andNumEqualTo(tickets.get(i).getNum());
            ticketStateMapper.deleteByExample(ticketStateExample);
        }
        ticketExample.createCriteria().andTicketInLogIdEqualTo(ticketInLog.getId());
        ticketMapper.deleteByExample(ticketExample);

        for (int i = 0; i < ticketInLog.getTotalNum(); i++) {
            Ticket ticket = new Ticket();
            ticket.setInTime(new Date());
            ticket.setTicketInLogId(ticketInLog.getId());
            ticket.setContent("新增入库");
            ticket.setTicketofficeId(Ticket.default_ticketOffice_id);
            ticket.setNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticketList.add(ticket);
        }
        ticketMapper.insertCount(ticketList);
        ticketInLogMapper.updateByPrimaryKeySelective(ticketInLog);

    }


}
