package com.refactor.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@TableName("emps")
@ToString
@Data
@NoArgsConstructor
public class Employee implements Serializable {
    @TableId(type = AUTO)
    private Integer id;
    private String lastName;

    private String email;
    //1 male, 0 female
    private Integer gender;
    private Integer deptid;
    private Date birth;
    @TableField(exist = false)
    private Department department;
}
