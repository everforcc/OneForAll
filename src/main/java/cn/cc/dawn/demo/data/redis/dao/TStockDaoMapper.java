package cn.cc.dawn.demo.data.redis.dao;

public interface TStockDaoMapper {

    int selectCount(int id);

    int updateCount(int id,int stocknum);

}
