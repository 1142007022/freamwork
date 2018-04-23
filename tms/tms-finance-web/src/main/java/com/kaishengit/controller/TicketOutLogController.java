package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.dto.Result;
import com.kaishengit.entitys.TicketOutLog;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.TicketOutLogService;
import com.kaishengit.service.TicketofficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@RequestMapping("/store")
@Controller
public class TicketOutLogController {
    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private TicketOutLogService ticketOutLogService;

    @PostMapping("/ticket/out/update/{id}")
    public String update(TicketOutLog ticketOutLog){
        ticketOutLogService.update(ticketOutLog);
        return "redirect:/store/ticket/out";
    }


    @GetMapping("/ticket/out/update/{id}")
    public String update(@PathVariable Integer id,Model model){
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        TicketOutLog ticketOutLog = ticketOutLogService.findById(id);
        model.addAttribute("ticketOutLog",ticketOutLog);
        return "store/ticketOutLog/update";
    }


    @GetMapping("/ticket/out/del/{id}")
    @ResponseBody
    public Result del(@PathVariable Integer id){
        ticketOutLogService.del(id);
        Result result = Result.success();
        return result;
    }

    @PostMapping("/ticket/out/add")
    public String add(TicketOutLog ticketOutLog,RedirectAttributes redirectAttributes){
        try {
            ticketOutLogService.add(ticketOutLog);
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/store/ticket/out";
    }
    @GetMapping("/ticket/out/add")
    public String add(Model model){
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        model.addAttribute("date",new Date());
        return "store/ticketOutLog/add";
    }

    @GetMapping("/ticket/out")
    public String ticketOutHome(Model model,@RequestParam(defaultValue = "1",required = false) Integer p){
        PageInfo<TicketOutLog> pageInfo = ticketOutLogService.findAll(p);
        model.addAttribute("pageInfo",pageInfo);
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "store/ticketOutLog/home";
    }


}
