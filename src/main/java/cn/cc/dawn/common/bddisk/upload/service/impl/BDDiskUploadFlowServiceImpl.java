/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 14:34
 */

package cn.cc.dawn.common.bddisk.upload.service.impl;

import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFileCreateService;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFilePreUploadService;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFileSliceService;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskUploadFlowService;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * bddisk 完整的上传流程
 */
@Slf4j
@Service
public class BDDiskUploadFlowServiceImpl implements IBDDiskUploadFlowService {

    @Autowired
    IBDDiskFilePreUploadService ibdDiskFilePreUploadService;
    @Autowired
    IBDDiskFileSliceService ibdDiskFileSliceService;
    @Autowired
    IBDDiskFileCreateService ibdDiskFileCreateService;

    @Override
    public boolean upload(BDFileVo bdFileVo, int userid) {
        log.info("上传网盘文件 start");
        BDFileVo bdFileResult = ibdDiskFilePreUploadService.preUploadFile(bdFileVo, userid);
        // 如果成功，那么不再执行分片上传流程，
        if(bdFileResult.getUploadResult()){
            return true;
        }
        // 如果没有成功执行分片上传逻辑
        ibdDiskFileSliceService.sliceUploadFile(bdFileResult, userid);
        // 合并逻辑
        ibdDiskFileCreateService.createUploadFile(bdFileResult, userid);
        log.info("上传网盘文件 end");
        return false;
    }
}
