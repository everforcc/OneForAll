package cn.cc.dawn.common.bddisk.accesstoken.serv;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;

/**
 * 用户token和数据库交互
 */
public interface IBDDiskTokenResultDtoService {

    /**
     * 新增用户token
     *
     * @param bdDiskTokenResultDto 用户token信息
     * @param userid               用户id
     * @return 插入结果
     */
    int insert(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    /**
     * 更新用户token
     *
     * @param bdDiskTokenResultDto 用户token信息
     * @param userid               用户id
     * @return 更新结果
     */
    int update(BDDiskTokenResultDto bdDiskTokenResultDto, int userid);

    /**
     * 查询用户token
     *
     * @param userid 用户id
     * @return 用户token信息
     */
    BDDiskTokenResultDto select(int userid);

}
