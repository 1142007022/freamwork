package com.kaishengit.controller;

import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.TicketService;
import com.kaishengit.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/ticket")
@Controller
public class TicketCountController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/count")
    public String home(Model model){
        ShiroUtil shiroUtil  = new ShiroUtil();
        Ticketoffice ticketoffice = shiroUtil.getTicketoffice();
        Map<String,Integer> map = ticketService.count(ticketoffice.getId());
        model.addAttribute("map",map);
        return "count/home";
    }
}
