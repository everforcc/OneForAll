/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 17:40
 * Copyright
 */

package cn.cc.dawn.common.bddisk.dto;

import cn.cc.dawn.common.bddisk.constant.BDDiskConstant;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.http.HttpParamUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 用refresh_token刷新access_token
 */
public class BDDiskRefreshTokenDto {

    private String grant_type = BDDiskConstant.refresh_token;
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
