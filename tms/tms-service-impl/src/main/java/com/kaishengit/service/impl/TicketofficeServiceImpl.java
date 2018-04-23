package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.*;
import com.kaishengit.mapper.SaleAccountMapper;
import com.kaishengit.mapper.TicketInLogMapper;
import com.kaishengit.mapper.TicketMapper;
import com.kaishengit.mapper.TicketofficeMapper;
import com.kaishengit.service.TicketofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketofficeServiceImpl implements TicketofficeService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketInLogMapper ticketInLogMapper;

    @Autowired
    private TicketofficeMapper ticketofficeMapper;

    @Autowired
    private SaleAccountMapper saleAccountMapper;
    @Override
    public PageInfo<Ticketoffice> findAll(Integer p) {
        PageHelper.startPage(p,5);
        List<Ticketoffice> ticketofficeList = ticketofficeMapper.findAll();
        return new PageInfo<>(ticketofficeList);
    }

    @Override
    public List<Ticketoffice> findAll() {
        return ticketofficeMapper.selectByExample(null);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketofficeAndSaleAccount(Ticketoffice ticketoffice, SaleAccount saleAccount) {
        ticketofficeMapper.insert(ticketoffice);
        saleAccount.setTicketofficeId(ticketoffice.getId());
        saleAccountMapper.insert(saleAccount);
        ticketoffice.setSaleAccountId(saleAccount.getId());
        ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delTickAndAccountByTickId(Integer id) {
        //删除售票点信息
        TicketofficeExample ticketofficeExample = new TicketofficeExample();
        ticketofficeExample.createCriteria().andIdEqualTo(id);
        ticketofficeMapper.deleteByExample(ticketofficeExample);

        //删除这个售票点所有人的信息
        SaleAccountExample saleAccountExample = new SaleAccountExample();
        saleAccountExample.createCriteria().andTicketofficeIdEqualTo(id);
        saleAccountMapper.deleteByExample(saleAccountExample);

        //从年票表中删除这个售票点所拥有的年票
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketofficeIdEqualTo(id);
        ticketMapper.deleteByExample(ticketExample);


    }

    @Override
    public Ticketoffice findById(Integer id) {
        return ticketofficeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Ticketoffice ticketoffice) {
        ticketofficeMapper.updateByPrimaryKeySelective(ticketoffice);
    }
}
