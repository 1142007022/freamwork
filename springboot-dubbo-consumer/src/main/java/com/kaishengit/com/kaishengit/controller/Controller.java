package com.kaishengit.com.kaishengit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kaishengit.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @Reference(version = "1.0")
    private TestService testService;

    @GetMapping("/{id}")
    public String test(@PathVariable Integer id){
        return testService.findById(id);
    }

}
