package com.refactor.demo.controller;

import com.refactor.demo.Dao.DepartmentMapper;
import com.refactor.demo.Dao.EmployeeMapper;
import com.refactor.demo.entities.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller

@RequestMapping("/main")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    //@Cacheable(cacheNames = "emps")
    @GetMapping("/emp/{id:[0-9]+}")
    public ModelAndView toChangePage(@PathVariable("id")Integer id, Map<String,Object>map) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("emp", employeeMapper.selectById(id));
        mv.setViewName("emps/change");
        return mv;
    }
    @GetMapping("/emp")
    String toAddPage(Map<String,Object>map) {
        map.put("departments", departmentMapper.selectList(null));
        return "emps/add";
    }
    @RequestMapping("/emps")
    String mainPage(Map<String,Object> map){
        map.put("emps", employeeMapper.getEmployeeList());
        return "emps/list";
    }
    @PostMapping("/emp")
    String addEmp(Employee employee){
        System.out.println(employee);
        employeeMapper.insert(employee);
        return "redirect:/main/emps";
    }
    @DeleteMapping("/emp/{id}")
    String delEmp(@PathVariable("id")Integer id) {
        employeeMapper.deleteById(id);
        return "redirect:/main/emps";
    }
    @PutMapping ("/emp")
    String updateEmp(Employee employee) {
        employeeMapper.updateById(employee);
        return "redirect:/main/emps";
    }

    @Cacheable(cacheNames = "emp")
    @RequestMapping("/emp_/{id}")
    @ResponseBody
    public Employee getemp(@PathVariable("id") Integer id){
        Employee employee = employeeMapper.selectById(id);
        System.out.println(employee);
        return employee;
    }
}
