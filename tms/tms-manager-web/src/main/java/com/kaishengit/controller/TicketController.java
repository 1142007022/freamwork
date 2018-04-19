package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.service.SaleAccountService;
import com.kaishengit.service.TicketofficeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class TicketController {

    @Autowired
    private TicketofficeService ticketofficeService;
    @Autowired
    private SaleAccountService saleAccountService;


    @PostMapping("/ticketoffice/update/{tickId}")
    public String update(@PathVariable Integer tickId,Ticketoffice ticketoffice,SaleAccount saleAccount){
        ticketoffice.setId(tickId);
        saleAccount.setId(ticketofficeService.findById(tickId).getSaleAccountId());
        ticketofficeService.update(ticketoffice);
        saleAccountService.update(saleAccount);
        return "redirect:/manager/ticketoffice";
    }

    @GetMapping("/ticketoffice/update/{id}")
    public String update(@PathVariable Integer id,Model model){
        Ticketoffice ticketoffice = ticketofficeService.findById(id);
        SaleAccount saleAccount = saleAccountService.findByTickId(id);
        model.addAttribute("ticketoffice",ticketoffice);
        model.addAttribute("saleAccount",saleAccount);
        return "manager/ticketoffice/update";
    }

    @GetMapping("/ticketoffice/del/{id}")
    @ResponseBody
    public Result delById(@PathVariable Integer id){
        ticketofficeService.delTickAndAccountByTickId(id);
        Result result = Result.success();
        return result;
    }


    @GetMapping("/ticketoffice")
    public String home(Model model) {
        List<Ticketoffice> ticketofficeList = ticketofficeService.findAll();
        model.addAttribute("ticketofficeList", ticketofficeList);
        return "manager/ticketoffice/home";
    }

    @GetMapping("/ticketoffice/add")

    public String add(Model model) {
        List<SaleAccount> saleAccountList = saleAccountService.findAll();
        model.addAttribute("saleAccountList", saleAccountList);
        return "manager/ticketoffice/add";
    }

    @PostMapping("/ticketoffice/add")
    public String add(Ticketoffice ticketoffice, SaleAccount saleAccount, RedirectAttributes redirectAttributes,Model model) {

        if (saleAccount.getMobile().length() != 11){
            model.addAttribute("ticketoffice",ticketoffice);
            model.addAttribute("message","请输入正确的手机号");
            model.addAttribute("account",saleAccount);
            return "manager/ticketoffice/add";
        }else{
            String password = saleAccount.getMobile().substring(5);
            String md5Password = DigestUtils.md5Hex(password);
            saleAccount.setPassword(md5Password);
        }

        ticketofficeService.saveTicketofficeAndSaleAccount(ticketoffice, saleAccount);
        return "redirect:/manager/ticketoffice";
    }

}
