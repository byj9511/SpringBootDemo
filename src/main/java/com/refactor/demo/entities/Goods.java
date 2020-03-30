package com.refactor.demo.entities;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class Goods {
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableId(type=IdType.ASSIGN_ID)
    private Long id;


    private String name;
    @Version
    @TableField(fill= FieldFill.INSERT)
    private Long version;
}
