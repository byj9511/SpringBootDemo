package com.byy.controller;

import com.byy.pojo.Dept;
import com.byy.service.ConsumerDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@DefaultProperties(defaultFallback="getTimeout")
@RestController
public class ConsumerDeptController {
    @Autowired
    ConsumerDeptService consumerDeptService;
    @GetMapping("consumer/dept/{id}")
    public Dept getDeptByid(@PathVariable("id")int id){
        return consumerDeptService.getDeptByid(id);
    }

    //@HystrixCommand(fallbackMethod = "getTimeout",commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    //})
    //@HystrixCommand
    @GetMapping("consumer/timeout/dept/{id}")
    Dept getDeptByidTimeout(@PathVariable("id") int id) {
        return consumerDeptService.getDeptByidTimeout(id);
    }

    public Dept getTimeout(int id){
        Dept dept = new Dept();
        dept.setName("出错了");
        return dept;
    }
}
