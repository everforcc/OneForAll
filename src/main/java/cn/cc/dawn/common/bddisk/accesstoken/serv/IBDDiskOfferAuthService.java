/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 18:06
 */

package cn.cc.dawn.common.bddisk.accesstoken.serv;

/**
 * 合并流程流程,
 * 提供给其他接口使用
 */
public interface IBDDiskOfferAuthService {

    /**
     * 给用户提供AccessToken
     *
     * @param userid 用户id
     * @return AccessToken
     */
    String offerAccessToken(int userid);

}
