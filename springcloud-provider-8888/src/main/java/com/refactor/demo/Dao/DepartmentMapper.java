package com.refactor.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
}
