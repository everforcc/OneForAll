/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-23 14:57
 * Copyright
 */

package cn.cc.dawn.common.bddisk.upload.controller;

import cn.cc.dawn.common.bddisk.upload.service.IBDDiskFilePreUploadService;
import cn.cc.dawn.common.bddisk.upload.service.IBDDiskUploadFlowService;
import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common/bddisk/upload")
public class BDDiskFileUploadController {

    @Autowired
    IBDDiskFilePreUploadService ibdDiskFilePreUploadService;
    @Autowired
    IBDDiskUploadFlowService ibdDiskUploadFlowService;

    @PostMapping("/preupload")
    public ResultE<BDFileVo> preUpload(@AuthenticationPrincipal CustomUser customUser, @RequestBody String json){
        return new ResultE<BDFileVo>().execute(e ->{
            e.setSuccess(ibdDiskFilePreUploadService.preUploadFile(JSONObject.parseObject(json, BDFileVo.class), customUser.getId()));
        });
    }

    @PostMapping("/flow")
    public ResultE<Boolean> flow(@AuthenticationPrincipal CustomUser customUser, @RequestBody String json){
        return new ResultE<Boolean>().execute(e ->{
            e.setSuccess(ibdDiskUploadFlowService.upload(JSONObject.parseObject(json, BDFileVo.class), customUser.getId()));
        });
    }

}
