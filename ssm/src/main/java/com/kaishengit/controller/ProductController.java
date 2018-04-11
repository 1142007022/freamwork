package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.enitys.Product;
import com.kaishengit.enitys.ProductType;
import com.kaishengit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @GetMapping("/{id:\\d+}")
    public String findBuId(@PathVariable Integer id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("pro",product);
        return "product/product";
    }


    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1",required = false) Integer p,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String place,
                       @RequestParam(required = false) Double minPrice,
                       @RequestParam(required = false) Double maxPrice,
                       @RequestParam(required = false) Integer typeId,
                       Model model
                       ){
        logger.debug("******查看列表");

        System.out.println("p----"+p);

        Map<String,Object> map = new HashMap<>();
        map.put("productName",productName);
        map.put("place",place);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("typeId",typeId);

        List<ProductType> typeList = productService.findAllType();
        PageInfo<Product> pageInfo = productService.findAllProductByPageNoAndQueryParam(p,map);
        //PageInfo<Product> pageInfo = productService.findByPage(p);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",typeList);
        return "product/list";
    }

    @GetMapping("/{id}/del")
    public String del(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        Product product = productService.findById(id);
        logger.debug("******删除{}",product.getProductName());
        productService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/product/list";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        List<ProductType> typeList = productService.findAllType();
        model.addAttribute("typeList",typeList);
        return "product/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(Product product){
        productService.update(product);
        return "redirect:/product/list";
    }


    @GetMapping("/new")
    public String addProduct(Model model){
        List<ProductType> typeList = productService.findAllType();
        model.addAttribute("typeList",typeList);
        return "product/new";
    }

    @PostMapping("/new")
    public String addProduct(Product product){
        productService.addProduct(product);
        logger.debug("******新增商品{}",product.getProductName());
        return "redirect:/product/list";
}

}
