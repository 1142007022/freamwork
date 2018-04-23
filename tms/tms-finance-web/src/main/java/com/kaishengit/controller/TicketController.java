package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.Account;
import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TicketInLogService;
import com.kaishengit.service.TicketService;
import com.kaishengit.service.TicketofficeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/store")
public class TicketController {

    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketInLogService ticketInLogService;

    @PostMapping("/ticket/in/add")
    @Transactional(rollbackFor = RuntimeException.class)
    public String addInLog(TicketInLog ticketInLog,RedirectAttributes redirectAttributes){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account)subject.getPrincipal();
        ticketInLog.setInAccountName(account.getAccName());

        try {
            TicketInLog ticketInLog1 = ticketInLogService.add(ticketInLog);
            ticketService.add(ticketInLog1);
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }


        return "redirect:/store/ticket/in";
    }

    @GetMapping("/ticket/in/add")
    public String addInlog(Model model){
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        Date date = new Date();
        String now = date.toLocaleString();
        model.addAttribute("date",now);
        return "store/ticketInLog/add";
    }


    @GetMapping("/ticket/in")
    public String ticketIn(Model model,@RequestParam(defaultValue = "1",required = false) Integer p){
        PageInfo<TicketInLog> pageInfo = ticketInLogService.findAll(p);
        model.addAttribute("pageInfo",pageInfo);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "store/ticketInLog/home";
    }


}
