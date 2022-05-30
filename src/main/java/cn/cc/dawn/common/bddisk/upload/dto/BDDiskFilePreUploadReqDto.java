/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 09:40
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.dto;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.utils.constant.CommonCharConstant;
import cn.cc.dawn.utils.file.IFile;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import cn.cc.dawn.utils.commons.web.HttpParamUtils;
import cn.cc.dawn.utils.annotation.ReflectFileFiled;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * bddisk 文件预上传
 */
@Getter
@Setter
public class BDDiskFilePreUploadReqDto {

    /**
     * 本接口固定为precreate
     */
    private String method = "precreate";
    /**
     * 接口鉴权认证参数，标识用户
     */
    @NotEmpty
    private String access_token;
    /**
     * 上传后使用的文件绝对路径，需要urlencode
     */
    //@ReflectFileFiled(use = false)
    @NotEmpty
    private String path;
    /**
     * 文件和目录两种情况：上传文件时，表示文件的大小，单位B；上传目录时，表示目录的大小，目录的话大小默认为0
     */
    //@ReflectFileFiled(use = false)
    @NotEmpty
    private long size;
    /**
     * 是否为目录，0 文件，1 目录
     */
    //@ReflectFileFiled(use = false)
    @NotEmpty
    private int isdir;
    /**
     * 文件各分片MD5数组的json串。block_list的含义如下，如果上传的文件小于4MB，其md5值（32位小写）即为block_list字符串数组的唯一元素；如果上传的文件大于4MB，需要将上传的文件按照4MB大小在本地切分成分片，不足4MB的分片自动成为最后一个分片，所有分片的md5值（32位小写）组成的字符串数组即为block_list。
     */
    //@ReflectFileFiled(use = false)
    @NotEmpty
    private List<String> block_list;
    /**
     * 固定值1
     */
    //@ReflectFileFiled(use = false)
    private int autoinit = 1;

    /**
     * 文件命名策略，默认为0。
     * 0 表示不进行重命名，若云端存在同名文件返回错误
     * 1 表示当path冲突时，进行重命名
     * 2 表示当path冲突且block_list不同时，进行重命名
     * 3 当云端存在同名文件时，对该文件进行覆盖
     */
    //@ReflectFileFiled(use = false)
    private int rtype = 1;
    /**
     * 上传ID
     */
    //@ReflectFileFiled(use = false)
    private String uploadid;

    /**
     * 注意这个位置的参数 五法 命名为 - 只能用_先代替
     */
    /**
     * 文件MD5，32位小写
     */
    @NotEmpty
    @JSONField(name = "content-md5")
    @ReflectFileFiled(use = true,alias = "content-md5")
    private String content_md5;
    /**
     * 文件校验段的MD5，32位小写，校验段对应文件前256KB
     */
    @NotEmpty
    @JSONField(name = "slice-md5")
    @ReflectFileFiled(use = true,alias = "slice-md5")
    private String slice_md5;
    /**
     * 客户端创建时间， 默认为当前时间戳
     */
    @ReflectFileFiled
    private String local_ctime;
    /**
     * 客户端修改时间，默认为当前时间戳
     */
    @ReflectFileFiled
    private String local_mtime;

    public String toStringWithBaseUrl(String guideUserAuthBaseUrl) {
        return guideUserAuthBaseUrl + CommonCharConstant.QUERY_SPLIT + HttpParamUtils.asUrlParams(this);
    }

    private BDDiskFilePreUploadReqDto() {

    }

    @SneakyThrows
    public BDDiskFilePreUploadReqDto(BDFileVo bdFileVo , String access_token) {
        //this.path = URLEncoder.encode(targetPath, CharsetsEnum.UTF_8.charset.toString());
        this.path = bdFileVo.getTargetPath();
        this.access_token = access_token;
        dealFileData(bdFileVo);
    }

    @ReflectFileFiled(use = false)
    private static IFile apacheiFile = new FileApacheUtils();

    private void dealFileData(BDFileVo bdFileVo){
        String localPath = bdFileVo.getLocalPath();
        // 1 目录
        if(apacheiFile.isDir(localPath)){
            this.isdir = 1;
            this.size = 0;
        }else {
            this.isdir = 0;
            this.size = apacheiFile.fileSizeByte(localPath);
        }
        BDFileVo bdFileVoResult = apacheiFile.block_list(localPath, false);
        List<String> stringList = bdFileVoResult.getBlock_list();

        bdFileVo.setFiles(bdFileVoResult.getFiles());

        this.block_list = new ArrayList<>();
        for(String str:stringList){
            block_list.add("\"" + str + "\"");
        }
        bdFileVo.setBlock_list(block_list);

        this.content_md5 = apacheiFile.md5(localPath);
        this.slice_md5 = apacheiFile.slice_md5(localPath,0L);
    }

    public static void main(String[] args) {
        //BDDiskFilePreUploadReqDto bdDiskFilePreUploadReqDto = new BDDiskFilePreUploadReqDto();
    }

}
