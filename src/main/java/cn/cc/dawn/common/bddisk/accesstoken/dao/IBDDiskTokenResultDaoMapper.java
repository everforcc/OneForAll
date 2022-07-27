package cn.cc.dawn.common.bddisk.accesstoken.dao;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;
import org.apache.ibatis.annotations.Param;

/**
 * 用户token
 */
public interface IBDDiskTokenResultDaoMapper {

    int insert(BDDiskTokenResultDto bdDiskTokenResultDto);

    int update(BDDiskTokenResultDto bdDiskTokenResultDto);

    //int markDelete(@Param("createUserid") int createUserid);

    BDDiskTokenResultDto select(@Param("createUserid") int createUserid);

}
