package cn.cc.dawn.common.bddisk.upload.api;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

/**
 * 1. 预上传
 */
public interface IBDDiskFilePreUploadApi {

    /**
     * 预上传接口
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 文件信息
     */
    BDFileVo preUploadFile(BDFileVo bdFileVo, int userid);

}
