package com.kaishengit.controller;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.SaleAccountService;
import com.kaishengit.service.TicketofficeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class TicketController {

    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private SaleAccountService saleAccountService;

    @GetMapping("/ticketoffice")
    public String home(Model model){
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList",ticketofficeList);
        return "manager/ticketoffice/home";
    }

    @GetMapping("/ticketoffice/add")

    public String add(Model model){
        List<SaleAccount> saleAccountList = saleAccountService.findAll();
        model.addAttribute("saleAccountList",saleAccountList);
        return "manager/ticketoffice/add";
    }

    @PostMapping("/ticketoffice/add")
    public String add(Ticketoffice ticketoffice,SaleAccount saleAccount){


        String password = saleAccount.getMobile().substring(5);
        String  md5Password = DigestUtils.md5Hex(password);
        saleAccount.setPassword(md5Password);
        ticketofficeService.saveTicketofficeAndSaleAccount(ticketoffice,saleAccount);
        return "redirect:/manager/ticketoffice";
    }

}
