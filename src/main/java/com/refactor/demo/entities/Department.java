package com.refactor.demo.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

//如果通过命名方式转换能使类名与表名对应上，则tablename不是必须的
@TableName("department")
@ToString
@Data
@NoArgsConstructor
public class Department implements Serializable {

	private Integer id;
	@TableField("name")
	private String departmentName;

}