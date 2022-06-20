/**
 * @Description
 * @Author everforcc
 * @Date 2022-06-20 20:30
 * Copyright
 */

package cn.cc.dawn.demo.data.mybaties.dao;

import cn.cc.dawn.demo.data.mybaties.dto.MybatisUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MybatisDao {

    /**
     * 用foreach查询
     */
    List<String> pagesIn(@Param("list") List<Integer> list);

    /**
     * 批量插入
     *
     * @param mybatisUserList 要插入的数据
     * @return 返回插入结果, 要么全部成功，要么失败
     */
    int insertList(List<MybatisUser> mybatisUserList);

}
