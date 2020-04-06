package com.refactor.demo.controller;

import entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ForDebug {
    @RequestMapping("/debug")
    public String success(@RequestParam(value="idd",required = false) String id,
                          String name,
                          HttpServletRequest request,
                          Map<String,Object> map,
                          Employee employee){
        map.put("time", "2020年");
        System.out.println("来带success页面");
        System.out.println(name);
        System.out.println(employee);
        return "success";
    }


}
