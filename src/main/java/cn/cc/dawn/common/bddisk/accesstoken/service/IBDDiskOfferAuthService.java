/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 18:06
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
