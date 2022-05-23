package cn.cc.dawn.common.bddisk.upload.service;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

public interface IBDDiskFilePreUploadService {

    BDFileVo preUploadFile(BDFileVo bdFileVo, int userid);

}
