package cn.cc.dawn.common.bddisk.uinfo.api;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

/**
 * 请求接口
 */
public interface BDDiskUInfoReqApi {

    BDDiskUInfoResDto getUInfo(int userid, boolean coverMsg);

}
