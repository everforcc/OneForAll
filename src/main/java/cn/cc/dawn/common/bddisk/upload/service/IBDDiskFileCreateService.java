/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 18:53
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.service;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;

public interface IBDDiskFileCreateService {

    boolean createUploadFile(BDFileVo bdFileVo, int userid);

}
