package com.byy.service;

import com.byy.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="DEPT-PROVIDER",fallback=ConsumerDeptFallbackService.class)
//@FeignClient(name="DEPT-PROVIDER")
@Service
//要用interface和mybatis有点像
public interface ConsumerService {
    //方法名随意，重要的地址和服务名（restTemplate需要？）
    @GetMapping("provider/dept/{id}")
    Dept getDeptByid(@PathVariable("id") int id);


    @GetMapping("provider/timeout/dept/{id}")
    Dept getDeptByidTimeout(@PathVariable("id") int id);




}
