/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 18:55
 */

package cn.cc.dawn.common.bddisk.upload.dto;

import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.annotation.ReflectFileFiled;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BDDiskFileCreateUploadReqDto {

    private String method = "create";

    private String access_token;

    @ReflectFileFiled(use = false)
    private String path;

    @ReflectFileFiled(use = false)
    private int size;

    @ReflectFileFiled(use = false)
    private int isdir;

    @ReflectFileFiled(use = false)
    private List<String> block_list;

    @ReflectFileFiled(use = false)
    private String uploadid;

    private BDDiskFileCreateUploadReqDto() {
    }

    public BDDiskFileCreateUploadReqDto(String access_token) {
        this.access_token = access_token;
    }

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

}
