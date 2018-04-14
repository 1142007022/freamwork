package com.kaishengit.controller;

import com.kaishengit.entitys.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    public AccountService accountService;

    @GetMapping
    public String home(HttpServletRequest req, HttpServletResponse resp) {
        String checked = isRemember(req, resp).get(0);
        String username = isRemember(req, resp).get(1);
        req.setAttribute("checked", checked);
        req.setAttribute("username", username);
        return "login";
    }

    @PostMapping
    public String login(String mobile, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model, String remember) {
        String loginIp = request.getRemoteAddr();
        Cookie(remember, mobile, request, response);
        try {
            Account account = accountService.login(mobile, password, loginIp);
            if (account != null) {
                model.addAttribute("acc", account);
                return "home";
            }
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("mobile", mobile);
        return "redirect:/";
    }


    public void Cookie(String checked, String mobile, HttpServletRequest req, HttpServletResponse resp) {
        if (StringUtils.isNotEmpty(checked)) {
            Cookie cookie = new Cookie("mobile", mobile);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 60 * 60);
            cookie.setDomain("19.168.1.73");
            cookie.setPath("/");
            resp.addCookie(cookie);
        } else {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if ("mobile".equals(cookie.getName())) {
                    cookie.setDomain("localhost");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);

                    resp.addCookie(cookie);
                }
            }
        }
    }


    public List<String> isRemember(HttpServletRequest req, HttpServletResponse resp) {
        String checked = "";
        String username = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("mobile".equals(cookie.getName())) {
                username = cookie.getValue();
                checked = "checked";
                break;
            }
        }

        List<String> lists = new ArrayList<>();
        lists.add(checked);
        lists.add(username);
        return lists;
    }


}
