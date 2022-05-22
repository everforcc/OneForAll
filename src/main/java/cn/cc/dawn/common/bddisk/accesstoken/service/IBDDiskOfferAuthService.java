/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 18:06
 * Copyright
 */

package cn.cc.dawn.common.bddisk.accesstoken.service;

public interface IBDDiskOfferAuthService {

    /**
     * 给用户提供AccessToken
     * @param userid
     * @return
     */
    String offerAccessToken(int userid);

}
