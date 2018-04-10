package com.kaishengit.controller;

import com.kaishengit.enitys.Product;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id:\\d+}")
    public String findBuId(@PathVariable Integer id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("pro",product);
        return "product/product";
    }


}
