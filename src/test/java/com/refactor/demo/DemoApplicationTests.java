package com.refactor.demo;

import com.refactor.demo.Dao.DepartmentMapper;
import com.refactor.demo.Dao.EmployeeMapper;
import com.refactor.demo.Dao.GoodsMapper;
import com.refactor.demo.entities.Department;
import com.refactor.demo.entities.Employee;
import com.refactor.demo.entities.Goods;
import org.byy.hellospringbootautoconfigurer.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    HelloService helloService;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Test
    void mapperTest1(){
        Employee employee = employeeMapper.selectById(101);
        System.out.println(employee);
        Employee employeeandDepartment = employeeMapper.getEmployeeandDepartment(101);
        System.out.println(employeeandDepartment);
    }
    @Test
    void test2(){
        helloService.sayHello("byy");
    }
    @Test
    void forredis(){
        Department department = departmentMapper.selectById(102);
        redisTemplate.opsForValue().set("dept", department);
        stringRedisTemplate.opsForValue().set("eml", "dsa");
        Object dept = redisTemplate.opsForValue().get("dept");
        System.out.println((Department)dept);
    }
    @Test
    void passwordtest(){
        String s="123";
        System.out.println(bCryptPasswordEncoder.encode(s));
    }
    @Autowired
    GoodsMapper goodsMapper;
    @Test
    void goodsMapperTest(){
        Goods goods1 = new Goods();
        goods1.setName("牛奶");
        goodsMapper.insert(goods1);

    }
    @Test
    //测试乐观锁有没有使版本自增
    void goodsMapperTest2(){
        Goods goods = goodsMapper.selectById(1244660051524739074L);
        goods.setName("咖啡");
        goodsMapper.updateById(goods);
    }
}
