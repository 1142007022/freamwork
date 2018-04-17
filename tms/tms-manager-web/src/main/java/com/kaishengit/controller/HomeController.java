package com.kaishengit.controller;

import com.kaishengit.entitys.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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
public class HomeController {


    @Autowired
    public AccountService accountService;

    @GetMapping("/401")
    public String errorPage(){
        return "error/401";
    }

    @GetMapping("/")
    public String home(HttpServletRequest req, HttpServletResponse resp) {

        Subject subject = SecurityUtils.getSubject();
        System.out.println("isAuthenticated()?" + subject.isAuthenticated());
        System.out.println("isRemembered()?" + subject.isRemembered());
        if(subject.isAuthenticated()){
            //如果成立的话代表用户是登陆过的 是想退出登录
            System.out.println("安全退出");
            subject.logout();
        }

        if (subject.isRemembered()){
            //如果成立的话代表用户是点击了记住我  那么此处就应该直接跳转到主页
            System.out.println("直接进入主页");
            return "redirect:/home";
        }

        //以上两者都不成立的话  正常登录
        String checked = isRemember(req, resp).get(0);
        String username = isRemember(req, resp).get(1);
        req.setAttribute("checked", checked);
        req.setAttribute("username", username);
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/")
    public String login(String mobile, String password, HttpServletRequest request, RedirectAttributes redirectAttributes,String remember) {
        Subject subject = SecurityUtils.getSubject();
        String ip = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile, DigestUtils.md5Hex(password),remember!=null,ip);
        try {
            subject.login(usernamePasswordToken);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);

            String url = "/home";
            if (savedRequest != null) {
                url = savedRequest.getRequestUrl();
            }
            return "redirect:" + url;
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {

            redirectAttributes.addFlashAttribute("message", "账户或密码错误");
        } catch (LockedAccountException ex) {
            redirectAttributes.addFlashAttribute("message", "账户状态异常");
        } catch (AuthenticationException ex) {
            redirectAttributes.addFlashAttribute("message", "账户或密码错误");
        }
        return "redirect:/";
    }



/*    @PostMapping("/")
    public String login(String mobile, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model, String remember) {
        String loginIp = request.getRemoteAddr();
        Cookie(remember, mobile, request, response);
        try {
            Account account = accountService.login(mobile, password, loginIp);
            if (account != null) {
                session.setAttribute("acc",account);
                //model.addAttribute("acc", account);
                return "redirect:/home";
            }
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("mobile", mobile);
        return "redirect:/";
    }*/


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
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if ("mobile".equals(cookie.getName())) {
                    username = cookie.getValue();
                    checked = "checked";
                    break;
                }
            }
        }


        List<String> lists = new ArrayList<>();
        lists.add(checked);
        lists.add(username);
        return lists;
    }


}
