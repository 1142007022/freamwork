package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entitys.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.TicketSaleService;
import com.kaishengit.service.TicketService;
import com.kaishengit.service.TicketStateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketSaleController  {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketStateService ticketStateService;
    @Autowired
    private TicketSaleService ticketSaleService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/sale/add")
    public String add(TicketSale ticketSale, RedirectAttributes redirectAttributes,String sex,String customerId){
        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();
        ticketSale.setTicketofficeId(ticketoffice.getId());
        ticketSale.setTicketofficeName(ticketoffice.getName());

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        customer.setIdCardKey(customerId);
        customer.setSex(sex);
        customer.setName(ticketSale.getCustomerName());

        try {
            customerService.add(customer);
            ticketSaleService.add(ticketSale);
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "sale/add";
        }
        return "redirect:/ticket/sale";
    }

    @GetMapping("/sale/add")
    public String add(Model model){
        //获取当前售票点的年票
        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();
        List<Ticket> ticketList = ticketService.findByTicketofficeId(ticketoffice.getId());
        List<TicketState> ticketStates = ticketStateService.findAll();
        List<Ticket> list = new ArrayList<>();
        for (int i = 0; i < ticketList.size();i++){
            for (int j = 0;j < ticketStates.size();j++){
                if (ticketList.get(i).getNum().equals(ticketStates.get(j).getTicketNum()) && ticketStates.get(j).getState().equals(TicketState.gived_state)){
                    list.add(ticketList.get(i));
                }
            }
        }
        model.addAttribute("list",list);
        return "sale/add";
    }

    @GetMapping("/sale")
    public String home(@RequestParam(required = false,defaultValue ="1") Integer p, Model model){
        PageInfo<TicketSale> pageInfo = ticketSaleService.findAll(p);
        model.addAttribute("pageInfo",pageInfo);
        return "sale/home";
    }

}
