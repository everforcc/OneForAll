package cn.cc.dawn.common.bddisk.upload.flow;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

/**
 * 文件上传总流程
 */
public interface IBDDiskUploadFlow {

    /**
     * 1. 预上传
     * 2. 分片
     * 3. 合并
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 上传结果
     */
    boolean upload(BDFileVo bdFileVo, int userid);

}
