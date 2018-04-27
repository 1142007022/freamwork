package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import com.kaishengit.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ticket")
public class RenewController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/renew/{ticketNum}")
    @ResponseBody
    public Result renew(@PathVariable String  ticketNum){
        ticketService.renew(ticketNum);
        return Result.success();
    }

}
