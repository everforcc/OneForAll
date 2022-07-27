package cn.cc.dawn.common.bddisk.uinfo.dao;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

/**
 * 网盘用户信息
 */
public interface BDDiskUInfoResMapper {

    int insert(BDDiskUInfoResDto bdDiskUInfoResDto);

    BDDiskUInfoResDto select(int userid);

}
