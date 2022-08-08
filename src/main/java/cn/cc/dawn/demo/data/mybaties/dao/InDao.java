package cn.cc.dawn.demo.data.mybaties.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InDao {

    /**
     * 处理in查询超过1000的问题
     *
     * @param stringList 查询条件
     * @return 返回结果
     */
    List<String> whereIn(@Param("stringList") List<String> stringList);

}
