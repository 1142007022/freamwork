package com.kaishengit.controller;

import com.kaishengit.entitys.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    public AccountService accountService;

    @GetMapping
    public String home(){
        return "login";
    }

    @PostMapping
    public String login(String mobile, String password, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model){
        String loginIp = request.getRemoteAddr();
        try {
            Account account = accountService.login(mobile,password,loginIp);
            if(account != null) {
                return "account/home";
            }
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        model.addAttribute("mobile",mobile);
        return "redirect:/";
    }

}
