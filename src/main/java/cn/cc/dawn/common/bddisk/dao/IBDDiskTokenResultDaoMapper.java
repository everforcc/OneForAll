package cn.cc.dawn.common.bddisk.dao;

import cn.cc.dawn.common.bddisk.dto.BDDiskTokenResultDto;
import org.apache.ibatis.annotations.Param;

public interface IBDDiskTokenResultDaoMapper {

    int insert(BDDiskTokenResultDto bdDiskTokenResultDto);

    int update(BDDiskTokenResultDto bdDiskTokenResultDto);

    int markDelete(@Param("createUserid") int createUserid);

    BDDiskTokenResultDto select(@Param("createUserid") int createUserid);

}
