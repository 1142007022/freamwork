package com.kaishengit.service.impl;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.SaleAccountExample;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.entitys.TicketofficeExample;
import com.kaishengit.mapper.SaleAccountMapper;
import com.kaishengit.mapper.TicketofficeMapper;
import com.kaishengit.service.TicketofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketofficeServiceImpl implements TicketofficeService {

    @Autowired
    private TicketofficeMapper ticketofficeMapper;

    @Autowired
    private SaleAccountMapper saleAccountMapper;
    @Override
    public List<Ticketoffice> findAll() {
        return ticketofficeMapper.findAll();
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
        TicketofficeExample ticketofficeExample = new TicketofficeExample();
        ticketofficeExample.createCriteria().andIdEqualTo(id);
        ticketofficeMapper.deleteByExample(ticketofficeExample);

        SaleAccountExample saleAccountExample = new SaleAccountExample();
        saleAccountExample.createCriteria().andTicketofficeIdEqualTo(id);
        saleAccountMapper.deleteByExample(saleAccountExample);
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
