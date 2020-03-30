package com.refactor.demo.componet;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptorPrehandle...");
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie:request.getCookies()){
                if (cookie.getName().equals("alreadylogin")){
                    return cookie.getValue().equals("1");
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/");
        return false;
    }
}
