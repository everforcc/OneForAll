/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-31 09:30
 * Copyright
 */

package cn.cc.dawn.local.craw.business.zhaimaoba.controller;

import cn.cc.dawn.local.craw.business.zhaimaoba.flow.IZMFlowService;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/open/zhaimao")
@RestController
@Slf4j
public class ZhaiMaoController {

    @Resource
    IZMFlowService izmFlowService;

    /**
     * 1. 下载每日一图某个地址下的数据
     *
     * @param url 要下载的地址,没做校验,下面有示例
     * @return 下载结果
     */
    @GetMapping("/mryt")
    public ResultE<Void> tFlow(@RequestParam("url") String url) {
        return new ResultE<Void>().call(() -> {
            izmFlowService.flow_MRYT(url);
        });
    }

}
