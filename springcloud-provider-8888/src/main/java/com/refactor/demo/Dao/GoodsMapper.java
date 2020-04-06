package com.refactor.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entities.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
}
