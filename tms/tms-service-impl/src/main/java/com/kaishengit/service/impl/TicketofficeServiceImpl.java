package com.kaishengit.service.impl;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;
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
}
