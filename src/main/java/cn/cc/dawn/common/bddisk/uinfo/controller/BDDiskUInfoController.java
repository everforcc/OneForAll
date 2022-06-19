/**
 * @Description
 * @Author everforcc
 * @Date 2022-05-22 11:45
 */

package cn.cc.dawn.common.bddisk.uinfo.controller;

import cn.cc.dawn.common.bddisk.uinfo.dto.BDDiskUInfoResDto;
import cn.cc.dawn.common.bddisk.uinfo.service.IBDDiskUInfoReqService;
import cn.cc.dawn.open.auth.dto.CustomUser;
import cn.cc.dawn.utils.entity.ResultE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common/bddisk/uinfo")
public class BDDiskUInfoController {

    @Autowired
    IBDDiskUInfoReqService ibdDiskUInfoReqService;

    @GetMapping("/info/{coverMsg}")
    public ResultE<BDDiskUInfoResDto> getUInfo(@AuthenticationPrincipal CustomUser customUser, @PathVariable boolean coverMsg){
        return new ResultE<BDDiskUInfoResDto>().execute(e ->{
            e.setSuccess(ibdDiskUInfoReqService.getUInfo(customUser.getId(),coverMsg));
        });
    }


}
