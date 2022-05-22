package cn.cc.dawn.common.bddisk.uinfo.service;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

public interface IBDDiskUInfoReqService {

    BDDiskUInfoResDto getUInfo(int userid, boolean coverMsg);

}
