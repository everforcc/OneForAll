package cn.cc.dawn.common.bddisk.upload.service;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

public interface IBDDiskUploadFlowService {

    boolean upload(BDFileVo bdFileVo, int userid);

}
