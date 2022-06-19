/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-19 16:18
 */

package cn.cc.dawn.common.bddisk.accesstoken.dto;

import cn.cc.dawn.common.bddisk.constant.BDDiskConstant;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;

/**
 * 用code换用户access_token
 */
public class BDDiskTokenReqDto {

    /**
     * 固定值，必须为authorization_code
     */
    private String grant_type = BDDiskConstant.authorization_code;
    // 用户授权后得到的code
    private String code = "f12bf99bc21b0e7d8b9899dff838736b";
    //
    private String client_id ;
    private String client_secret ;
    private String redirect_uri ;

    public void setCode(String code) {
        this.code = code;
    }

    private BDDiskTokenReqDto() {
    }

    public BDDiskTokenReqDto(String client_id, String client_secret, String redirect_uri) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
    }

    public String toStringWithBaseUrl(String access_tokenWebUrl) {
        return access_tokenWebUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

}
