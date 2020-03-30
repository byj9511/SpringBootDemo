package com.refactor.demo.auth;

import com.refactor.demo.componet.ImageVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ImageAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    MyFailureHandler myFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/userlogin")){ //一定要先匹配了登录路径在去验证请求
            try {
                validate(request);
            }
            catch (SessionAuthenticationException e){
                myFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
        return;

    }
    //进行验证，如果有异常产生，则说明验证失败，需要进行错误处理
    //错误前提：
    //1验证码过期
    //2验证码为空
    //3验证码对不上
    //private void validate(HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException{
    //    String captchaCode = request.getParameter("captchaCode");
    //    HttpSession session = request.getSession();
    //    ImageVo image = (ImageVo) session.getAttribute("image");
    //    if(image.isExpire())throw new SessionAuthenticationException("验证码过期");
    //    if(StringUtil.isNullOrEmpty(captchaCode))throw new SessionAuthenticationException("验证码为空");
    //    if(!image.getText().equals(captchaCode))throw new SessionAuthenticationException("验证码不正确");
    //}
    public void validate(HttpServletRequest request){
        HttpSession session = request.getSession();
        String number=request.getParameter("captchaCode");
        ImageVo imageVo = (ImageVo)session.getAttribute("image");
        if (imageVo.isExpire()) throw new SessionAuthenticationException("验证码超时");
        if (StringUtil.isNullOrEmpty(number)) throw new SessionAuthenticationException("验证码为空");
        if (!number.equals(imageVo.getText())) throw new SessionAuthenticationException("验证码错误");

    }
}
