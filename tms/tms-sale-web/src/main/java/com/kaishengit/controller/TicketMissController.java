package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.dto.Result;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketMissController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketStateService ticketStateService;
    @Autowired
    private TicketofficeService ticketofficeService;

    @PostMapping("/miss/{ticketNum}")
    @ResponseBody
    public Result miss(@PathVariable String ticketNum){
        ticketStateService.miss(ticketNum);
        return Result.success();
    }

    @GetMapping("/miss")
    public String miss(@RequestParam(defaultValue = "1",required = false)Integer p, Model model){
        //显示当前售票点的所有年票信息 除了已过期的都显示
        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();
        //根据当前登录帐号的售票点查找这个售票点符合条件的年票记录
        List<Ticket> ticketList = ticketService.findByTicketofficeId(ticketoffice.getId());
        //查找年票记录表
        PageInfo<TicketState> pageInfo = ticketStateService.findAllTicketStateOfTicketoffice(p);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketList",ticketList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "miss/home";
    }

}
