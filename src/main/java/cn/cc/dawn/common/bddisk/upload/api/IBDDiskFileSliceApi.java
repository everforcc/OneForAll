package cn.cc.dawn.common.bddisk.upload.api;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

/**
 * 2. 分片上传
 */
public interface IBDDiskFileSliceApi {

    /**
     * 分片上传
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 文件信息
     */
    BDFileVo sliceUploadFile(BDFileVo bdFileVo, int userid);

}