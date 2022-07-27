/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-22 10:20
 */

package cn.cc.dawn.common.bddisk.uinfo.dto;

import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.annotation.ReflectFileFiled;

/**
 * <a href="https://pan.baidu.com/union/doc/pksg0s9ns">...</a>
 * 网盘用户信息请求信息
 */
public class BDDiskUInfoReqDto {

    private final String method = "uinfo";

    private String access_token;

    //@ReflectFileFiled(alias = "userDefineName")
    @ReflectFileFiled(use = false)
    private String Host = "pan.baidu.com";

    private BDDiskUInfoReqDto() {
    }

    public BDDiskUInfoReqDto(String access_token) {
        this.access_token = access_token;
    }

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

    public static void main(String[] args) {
        BDDiskUInfoReqDto bdDiskUInfoReqDto = new BDDiskUInfoReqDto("access_token");
        System.out.println(bdDiskUInfoReqDto.toStringWithBaseUrl("guideUserAuthBaseUrl"));
    }

}
