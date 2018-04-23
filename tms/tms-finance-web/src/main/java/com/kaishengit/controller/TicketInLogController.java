package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.TicketInLogService;
import com.kaishengit.service.TicketofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/store")
public class TicketInLogController {

    private TicketController ticketController = new TicketController();

    @Autowired
    private TicketInLogService ticketInLogService;
    @Autowired
    private TicketofficeService ticketofficeService;


    @PostMapping("/ticketInLog/update/{id}")
    public String update(TicketInLog ticketInLog){
        ticketInLogService.update(ticketInLog);
        return "redirect:/store/ticket/in";
    }

    @GetMapping("/ticketInLog/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        TicketInLog ticketInLog = ticketInLogService.findById(id);
        model.addAttribute("ticketInLog",ticketInLog);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "store/ticketInLog/update";
    }


    @GetMapping("/ticketInLog/del/{id}")
    @ResponseBody
    public Result delById(@PathVariable Integer id){
        ticketInLogService.delById(id);
        System.out.println("---------"+id);
        Result result = Result.success();
        return result;
    }

}
