package com.kaishengit.controller;

import com.kaishengit.service.TicketStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TicketCountController {

    @Autowired
    private TicketStateService ticketStateService;

    @GetMapping("/store/ticket/count")
    public String home(Model model){
        Map<String,Object> count = ticketStateService.getCount();
        model.addAttribute("count",count);
        return "store/count/home";
    }

}
