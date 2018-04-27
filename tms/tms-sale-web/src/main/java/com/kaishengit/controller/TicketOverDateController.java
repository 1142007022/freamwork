package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.TicketService;
import com.kaishengit.service.TicketStateService;
import com.kaishengit.service.TicketofficeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketOverDateController {

    @Autowired
    private TicketStateService ticketStateService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketofficeService ticketofficeService;

    @GetMapping("/overdate")
    public String overDate(@RequestParam(defaultValue = "1",required = false) Integer p, Model model){

        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();


        PageInfo<TicketState> pageInfo = ticketStateService.findAll(p);
        model.addAttribute("pageInfo",pageInfo);
        List<Ticket> ticketList = ticketService.findByTicketofficeId(ticketoffice.getId());
        model.addAttribute("ticketList",ticketList);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "overdate/home";
    }

}
