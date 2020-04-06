package com.byy.service;

import org.springframework.stereotype.Component;

@Component
public class ConsumerDeptFallbackService implements ConsumerDeptService {
    @Override
    public Dept getDeptByid(int id) {
        Dept dept = new Dept();
        return dept;
    }

    @Override
    public Dept getDeptByidTimeout(int id) {
        Dept dept = new Dept();
        dept.setName("超时");
        return dept;
    }
}
