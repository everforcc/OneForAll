package cn.cc.dawn.common.bddisk.uinfo.service;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

public interface IBDDiskUInfoResService {

    int insert(BDDiskUInfoResDto bdDiskUInfoResDto, int userid);

    BDDiskUInfoResDto select(int userid);

}
