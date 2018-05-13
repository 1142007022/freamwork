package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home(HttpSession session, HttpServletRequest request){
        return "hello!" + "sessioID:" + session.getId() +";"+ "IP:" + request.getHeader("X-Real-IP");
    }

}
