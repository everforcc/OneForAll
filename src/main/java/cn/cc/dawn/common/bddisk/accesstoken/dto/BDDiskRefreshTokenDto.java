/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 17:40
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import cn.cc.dawn.common.bddisk.constant.BDDiskConstant;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;

/**
 * 用refresh_token刷新access_token
 */
public class BDDiskRefreshTokenDto {

    private final String grant_type = BDDiskConstant.refresh_token;
    private String refresh_token;
    private String client_id;
    private String client_secret;

    private BDDiskRefreshTokenDto() {
    }

    public BDDiskRefreshTokenDto(String client_id, String client_secret) {
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

}
