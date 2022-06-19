/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 17:13
 */

package cn.cc.dawn.common.bddisk.upload.dto;

import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.annotation.ReflectFileFiled;

/**
 * 分片上传
 */
public class BDDiskFileSliceUploadReqDto {

    private String method = "upload";

    private String access_token;

    private String type = "tmpfile";

    private String path;

    private String uploadid;

    private int partseq;

    @ReflectFileFiled
    private char[] file;

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

    public BDDiskFileSliceUploadReqDto(String access_token, String uploadid, String path) {
        this.access_token = access_token;
        this.uploadid = uploadid;
        this.path = path;
    }

    public int getPartseq() {
        return partseq;
    }

    public void setPartseq(int partseq) {
        this.partseq = partseq;
    }

    public char[] getFile() {
        return file;
    }

    public void setFile(char[] file) {
        this.file = file;
    }
}
