package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Homecontroller {

    @GetMapping("/test")
    public String home(){
        System.out.println("-------");
        return "index";
    }

}
