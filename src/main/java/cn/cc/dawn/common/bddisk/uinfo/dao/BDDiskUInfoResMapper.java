package cn.cc.dawn.common.bddisk.uinfo.dao;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

public interface BDDiskUInfoResMapper {

    int insert(BDDiskUInfoResDto bdDiskUInfoResDto);

    BDDiskUInfoResDto select(int userid);

}
