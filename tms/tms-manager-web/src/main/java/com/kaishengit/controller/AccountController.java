package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import com.kaishengit.entitys.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public String home(Model model){
        List<Account> accountList = accountService.findAll();
        model.addAttribute("accountList",accountList);
        return "/manager/account/home";
    }

    @GetMapping("/account/add")
    public String addAccount(){

        return "/manager/account/add";
    }

    @PostMapping("/account/add")
    @ResponseBody
    public Result addAccount(Account account, RedirectAttributes redirectAttributes){
        try {
            accountService.addAccount(account);
            Result result = Result.success();
            return result;
        }catch (ServiceException e){
            Result result = Result.error(e.getMessage());
            return result;
        }
    }
}
