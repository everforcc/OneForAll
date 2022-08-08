/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 16:02
 * Copyright
 */

package cn.cc.dawn.demo.callback.controller;

import cn.cc.dawn.demo.callback.service.IDownloadStatusCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/open/callback")
@RestController
@Slf4j
public class CallbackController {

    @Resource
    IDownloadStatusCallBack iDownloadStatusCallBack;

    /**
     * 回调示例代码
     */
    @GetMapping("/userCallback")
    public void userCallback() {
        log.info("回调接口测试");
        iDownloadStatusCallBack.download("www.baidu.com");
        log.info("回调接口完成");
    }

}
