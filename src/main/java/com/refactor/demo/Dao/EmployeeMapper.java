package com.refactor.demo.Dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.refactor.demo.entities.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    //如果用驼峰命名法能使数据表的字段名与bean属性名重合，那么不用显示申明
    @Results(id="empdept", value =
            @Result(column = "deptid",property = "department",
                    //用某个字段名进行多表联查，并将查询结果封装成对象，赋值给department属性
                    one=@One(select = "com.refactor.demo.Dao.DepartmentMapper.selectById"))
    )
    @Select("select * from emps where id=#{id}")
    Employee getEmployeeandDepartment(int id);
    @ResultMap("empdept")
    @Select("select * from emps")
    List<Employee> getEmployeeList();
}
