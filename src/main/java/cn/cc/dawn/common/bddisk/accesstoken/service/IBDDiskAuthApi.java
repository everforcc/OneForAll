package cn.cc.dawn.common.bddisk.accesstoken.service;

import cn.cc.dawn.common.bddisk.accesstoken.dto.BDDiskTokenResultDto;

/**
 * 用户认证流程
 */
public interface IBDDiskAuthApi {

    /**
     * 1. 返回用户扫码地址
     *
     * @return 用户扫码地址
     */
    String getUserAuthUrl();

    /**
     * 2. 获取用户token
     *
     * @param userid        用户系统id
     * @param code          扫码后返回的code
     * @param coverOldToken 是否要刷新token,如果true就表示强制刷新,默认使用旧的
     * @return 网盘对应的token
     */
    String getAccessToken(int userid, String code, boolean coverOldToken);

    /**
     * 3. 主动刷新token
     *
     * @param userid        用户id
     * @param refresh_token 刷新token需要的特殊token,之前接口有返回
     * @return 返回刷新后的token
     */
    BDDiskTokenResultDto refreshAccessToken(int userid, String refresh_token);

}
