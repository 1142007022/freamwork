package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.TicketOutLog;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.TicketOutLogService;
import com.kaishengit.service.TicketofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private TicketOutLogService ticketOutLogService;

    @Autowired
    private TicketofficeService ticketofficeService;

    @PostMapping("/pay/{id}")
    public String payUpdate(TicketOutLog ticketOutLog){
       ticketOutLogService.updatePayType(ticketOutLog);
        return "redirect:/finance/pay";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable Integer id,Model model){
        TicketOutLog ticketOutLog = ticketOutLogService.findById(id);
        model.addAttribute("ticketOutLog",ticketOutLog);
        return "finance/pay/pay";
    }

    @GetMapping("/pay")
    public String pay(Model model, @RequestParam(defaultValue = "1",required = false)Integer p){
        //查询所有的年票下发记录
        PageInfo<TicketOutLog> pageInfo = ticketOutLogService.findAll(p);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        model.addAttribute("pageInfo",pageInfo);
        return "finance/pay/home";
    }

}
