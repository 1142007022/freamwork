package com.kaishengit.controller;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.service.SaleAccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping("/401")
    public String errorPage() {
        return "error/401";
    }

    @GetMapping("/")
    public String home(HttpServletRequest req, HttpServletResponse resp) {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        if (subject.isRemembered()) {
            System.out.println("ֱ�ӽ�����ҳ");
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/")
    public String login(String mobile, String password, HttpServletRequest request, RedirectAttributes redirectAttributes, String remember) {
        Subject subject = SecurityUtils.getSubject();
        String ip = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile, DigestUtils.md5Hex(password), remember != null, ip);
        try {
            subject.login(usernamePasswordToken);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);

                String url = "/home";
                if (savedRequest != null) {
                    url = savedRequest.getRequestUrl();
                }
                logger.info("{} 登录",mobile);
                return "redirect:" + url;


        } catch (UnknownAccountException | IncorrectCredentialsException ex) {

            redirectAttributes.addFlashAttribute("message", "�˻����������");
        } catch (LockedAccountException ex) {
            redirectAttributes.addFlashAttribute("message", "�˻�״̬�쳣");
        } catch (AuthenticationException ex) {
            redirectAttributes.addFlashAttribute("message", "�˻����������");
        }
        return "redirect:/";
    }
}
