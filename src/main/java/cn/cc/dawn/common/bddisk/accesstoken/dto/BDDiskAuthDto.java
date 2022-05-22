/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-19 15:36
 * Copyright
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.http.HttpParamUtils;

/**
 * 引导用户授权类
 */
public class BDDiskAuthDto {

    private String response_type = "code";
    // api_key
    private String client_id;
    private String redirect_uri = "oob";
    private String scope = "basic,netdisk";
    private String display = "tv";
    private String qrcode = "1";
    private String force_login = "1";
    private String device_id = "820921428tp8x63q51";

    private BDDiskAuthDto() {
    }

    public BDDiskAuthDto(String client_id) {
        this.client_id = client_id;
    }

    public BDDiskAuthDto(String client_id, String redirect_uri) {
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
    }

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

}
