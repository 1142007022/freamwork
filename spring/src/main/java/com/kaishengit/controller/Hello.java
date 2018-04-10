package com.kaishengit.controller;


import com.kaishengit.enitys.Customer;
import com.kaishengit.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/customer")
@Controller
public class Hello {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @GetMapping("/add")
    public String add() {
        return "customer/add";
    }

    @PostMapping("/add")
    public String save(String name, String address) throws UnsupportedEncodingException {
        System.out.println("name>>>>>" + name);
        System.out.println("address>>>" + address);
        return "redirect:/customer/add";
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "1") Integer p, Model model) throws IOException {
     /*   if (1 == 1) {
            throw new IOException();
        }*/

        model.addAttribute("p", p);
        return "customer/list";
    }


    @GetMapping("/{id:\\d+}")
    public String viewCustomer(@PathVariable Integer id,
                               Model model) {

        System.out.println(id);
        if (id == 2) {
            throw new NotFoundException();
        }
        model.addAttribute("id", id);
        return "customer/customer";
    }

/*    @GetMapping("/{name}/{address}")
    public ModelAndView custWithAddress(@PathVariable String name, @PathVariable String address) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/customer");
        modelAndView.addObject("name", name);
        modelAndView.addObject("address", address);
        return modelAndView;
    }*/

    @GetMapping("/all.json")
    @ResponseBody
    public List<Customer> all() {
        Customer customer = new Customer();
        customer.setAddress("zz");
        customer.setName("jaingdong");

        Customer customer1 = new Customer();
        customer1.setAddress("zz");
        customer1.setName("hahah");

        Customer customer2 = new Customer();
        customer2.setAddress("jz");
        customer2.setName("tt");

        List<Customer> lists = new ArrayList<>();
        lists.add(customer);
        lists.add(customer1);
        lists.add(customer2);
        return lists;
    }

}
