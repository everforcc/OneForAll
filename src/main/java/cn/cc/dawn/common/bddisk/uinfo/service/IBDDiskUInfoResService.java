package cn.cc.dawn.common.bddisk.uinfo.service;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;

/**
 * 用户信息
 * 数据库交互
 */
public interface IBDDiskUInfoResService {

    /**
     * 入库
     *
     * @param bdDiskUInfoResDto 用户信息
     * @param userid            用户id
     * @return 新增结果
     */
    int insert(BDDiskUInfoResDto bdDiskUInfoResDto, int userid);

    /**
     * 查询用户信息
     *
     * @param userid 用户id
     * @return 用户信息
     */
    BDDiskUInfoResDto select(int userid);

}
