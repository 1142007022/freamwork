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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/ticket")
public class RegetController {
    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketStateService ticketStateService;

    @PostMapping("/reget/{ticketNum}")
    @ResponseBody
    public Result reget(@PathVariable String ticketNum){
        ticketService.getAgain(ticketNum);
        return Result.success();
    }


    @GetMapping("/reget")
    public String reget(@RequestParam(defaultValue = "1",required = false)Integer p, Model model){
        //查找当前登录售票点的已挂失年票
        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();
        PageInfo<TicketState> ticketStatePageInfo = ticketService.reget(p,ticketoffice);
        model.addAttribute("pageInfo",ticketStatePageInfo);
        List<Ticket> ticketList = ticketService.findAll();
        model.addAttribute("ticketList",ticketList);
        model.addAttribute("ticketoffice",ticketoffice);
        return "reget/home";
    }

}
