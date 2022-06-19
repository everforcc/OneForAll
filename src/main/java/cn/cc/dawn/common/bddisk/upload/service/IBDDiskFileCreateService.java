/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-23 18:53
 */

package cn.cc.dawn.common.bddisk.upload.service;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

public interface IBDDiskFileCreateService {

    boolean createUploadFile(BDFileVo bdFileVo, int userid);

}
