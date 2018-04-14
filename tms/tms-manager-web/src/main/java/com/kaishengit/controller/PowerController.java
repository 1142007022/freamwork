package com.kaishengit.controller;

import com.kaishengit.dto.Result;
import com.kaishengit.entitys.Power;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping("/exit")
    public String exit(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
       return "redirect:/";
    }


    @GetMapping("/power")
    public String home(Model model){
        List<Power> powerList = powerService.findAllPower();
        model.addAttribute("powerList",powerList);
        return "manager/power/home";
    }

    @GetMapping("/power/add")
    public String addPower(Model model){
        List<Power> powerList = powerService.findAllPower();
        model.addAttribute("powerList",powerList);
        return "manager/power/add";
    }

    @PostMapping("/power/add")
    public String addPower(Power power){
        powerService.addPower(power);
        return "redirect:/manager/power";
    }

    @GetMapping("/power/update/{id}")
    public String update(@PathVariable String id){
        System.out.println("-----"+id);
        return "";
    }

    @GetMapping("/power/del/{id}")
    @ResponseBody
    public Result delPowerById(@PathVariable Integer id){

        try {
            powerService.delPowerById(id);
            Result result = Result.success();
            return result;
        }catch (ServiceException e){
            Result result = Result.error(e.getMessage());
            return result;
        }
    }

}
