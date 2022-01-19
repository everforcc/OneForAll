package cn.cc.dawn.demo.data.redis.dao;

import cn.cc.dawn.demo.data.redis.dto.TOrderDto;

public interface TOrderDaoMapper {

    int insertOrder(TOrderDto tOrderDto);

    int selectCount(TOrderDto tOrderDto);

}
