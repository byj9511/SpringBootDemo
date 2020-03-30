package com.refactor.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping(value = {"/","/index"})
    public String mainPage(){
        System.out.println("来到main");
        return "login";
    }
    //关于用户的认证完全有security接管
    //@PostMapping("/userlogin")
    //public String loginPage(String username, String password, HttpServletResponse response){
    //    System.out.println(password);
    //    if (password.equals("123")){
    //        Cookie cookie = new Cookie("alreadylogin", "1");
    //        cookie.setMaxAge(30*60);
    //        response.addCookie(cookie);
    //    }
    //    return "dashboard";
    //}
}
