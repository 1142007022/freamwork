package com.kaishengit.controller;

import com.kaishengit.entitys.Power;
import com.kaishengit.entitys.Roles;
import com.kaishengit.service.PowerService;
import com.kaishengit.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class RolesController {

    @Autowired
    private PowerService powerService;
    @Autowired
    private RolesService rolesService;

    @GetMapping("/roles")
    public String home(Model model){
        List<Roles> rolesList = rolesService.findAllRoles();
        model.addAttribute("rolesList",rolesList);
        return "manager/roles/home";
    }

    @GetMapping("/roles/add")
    public String addRoles(Model model){
        List<Power> powerList = powerService.findAllPower();
        model.addAttribute("powerList",powerList);
        return "manager/roles/add";
    }

    @PostMapping("/roles/add")
    public String addRoles(Roles roles, Integer[] powerIds, RedirectAttributes redirectAttributes){

        rolesService.addRolesAndPowers(roles,powerIds);
        return "redirect:/manager/roles";
    }

}
