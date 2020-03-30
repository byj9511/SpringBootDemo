package com.refactor.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.refactor.demo.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
}
