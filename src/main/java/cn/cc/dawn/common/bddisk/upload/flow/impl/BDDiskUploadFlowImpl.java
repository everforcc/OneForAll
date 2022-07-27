/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 14:34
 */

package cn.cc.dawn.common.bddisk.upload.flow.impl;

import cn.cc.dawn.common.bddisk.upload.flow.IBDDiskUploadFlow;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFileCreateApi;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFilePreUploadApi;
import cn.cc.dawn.common.bddisk.upload.api.IBDDiskFileSliceApi;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * bddisk 完整的上传流程
 */
@Slf4j
@Service
public class BDDiskUploadFlowImpl implements IBDDiskUploadFlow {

    @Autowired
    IBDDiskFilePreUploadApi ibdDiskFilePreUploadApi;
    @Autowired
    IBDDiskFileSliceApi ibdDiskFileSliceApi;
    @Autowired
    IBDDiskFileCreateApi ibdDiskFileCreateApi;

    /**
     * 完整上传流程
     *
     * @param bdFileVo 文件信息
     * @param userid   用户id
     * @return 上传结果
     */
    @Override
    public boolean upload(BDFileVo bdFileVo, int userid) {
        log.info("上传网盘文件 start");
        BDFileVo bdFileResult = ibdDiskFilePreUploadApi.preUploadFile(bdFileVo, userid);
        // 如果成功，那么不再执行分片上传流程，
        if (bdFileResult.getUploadResult()) {
            return true;
        }
        // 如果没有成功执行分片上传逻辑
        ibdDiskFileSliceApi.sliceUploadFile(bdFileResult, userid);
        // 合并逻辑
        ibdDiskFileCreateApi.createUploadFile(bdFileResult, userid);
        log.info("上传网盘文件 end");
        return false;
    }
}
