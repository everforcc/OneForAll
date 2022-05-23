package cn.cc.dawn.common.bddisk.upload.service;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

public interface IBDDiskFileSliceService {

    BDFileVo sliceUploadFile(BDFileVo bdFileVo, int userid);

}