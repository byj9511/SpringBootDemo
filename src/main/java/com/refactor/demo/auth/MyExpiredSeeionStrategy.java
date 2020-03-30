package com.refactor.demo.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyExpiredSeeionStrategy implements SessionInformationExpiredStrategy {

    ObjectMapper objectMapper= new ObjectMapper();
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String,String> map=new HashMap<>();
        map.put("错误", "多个用户登陆");
        HttpServletResponse response=sessionInformationExpiredEvent.getResponse();
        //响应数据的格式
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
