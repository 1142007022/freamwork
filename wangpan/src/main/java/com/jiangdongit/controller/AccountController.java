package com.jiangdongit.controller;

import com.jiangdongit.entitys.Account;
import com.jiangdongit.result.Result;
import com.jiangdongit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/add")
    public String add(){
        return "account/add";
    }

    @PostMapping("/add")
    public Result add(Account account) {
        try {
            accountService.add(account);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
