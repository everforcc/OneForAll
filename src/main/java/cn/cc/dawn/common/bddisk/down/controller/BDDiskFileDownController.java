/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-24 10:17
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

/**
 * 下载接口
 * 测试下载程序,没走完整流程
 */
@RestController
@RequestMapping(value = "/common/bddisk/down")
public class BDDiskFileDownController {

    @Autowired
    IBDDiskFileDownService ibdDiskFileDownService;

    @GetMapping("/d")
    public ResultE<Boolean> d(@AuthenticationPrincipal CustomUser customUser) {
        return new ResultE<Boolean>().execute(e -> {
            e.setSuccess(ibdDiskFileDownService.downFile(customUser.getId()));
        });
    }

}
