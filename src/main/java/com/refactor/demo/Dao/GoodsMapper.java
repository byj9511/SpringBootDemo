package com.refactor.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.refactor.demo.entities.Goods;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
}
