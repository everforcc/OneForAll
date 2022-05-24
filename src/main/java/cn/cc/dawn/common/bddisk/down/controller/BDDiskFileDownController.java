/**
 * Project:TODO ADD PROJECT NAME OneForAll
 *
 * @Description
 * @Author Author Date Description
 * ------ ------ ------
 *    TODO 开发人员邮箱前缀 调整时间 年-月-日 主要改动点>5字符
 * @Date 2022-05-24 10:17
 * Copyright
 */

package cn.cc.dawn.common.bddisk.down.controller;

import cn.cc.dawn.common.bddisk.down.service.IBDDiskFileDownService;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common/bddisk/down")
public class BDDiskFileDownController {

    @Autowired
    IBDDiskFileDownService ibdDiskFileDownService;

    @GetMapping("/d")
    public ResultE<Boolean> d(@AuthenticationPrincipal CustomUser customUser){
        return new ResultE<Boolean>().execute(e ->{
            e.setSuccess(ibdDiskFileDownService.downFile(customUser.getId()));
        });
    }

}
