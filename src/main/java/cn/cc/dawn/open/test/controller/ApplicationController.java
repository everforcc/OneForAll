package cn.cc.dawn.open.test.controller;

import cn.cc.dawn.utils.commons.codec.JAESUtil;
import cn.cc.dawn.utils.commons.lang.RObjectsUtils;
import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@Slf4j
@RequestMapping("/open/application")
@RestController
public class ApplicationController {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private ExecutorService singleThread;

    @GetMapping("/app")
    public String app(){
        log.info(cacheManager.toString());
        return "executorService";
    }

    @GetMapping("/aeskey")
    public String defaultAesKey(){
        log.info(JAESUtil.DEFAULT_KEY);
        return JAESUtil.DEFAULT_KEY;
    }

    @GetMapping("/singleThread")
    public ResultE<String> singleThread(){
        /**
         * 在spring中需要使用 @Autowired来 实例
         * 在初始化之前使用，需要以下方法来   实例
         */
        //ExecutorService executorService = ApplicationContextInit.getMultiThread();
        log.info("是否为null: " + RObjectsUtils.isNull(singleThread));

        singleThread.execute(new Runnable() {
            public void run() {
                // 打印正在执行的缓存线程信息
                System.out.println(Thread.currentThread().getName()
                        + "正在被执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //executorService.shutdown();
        return new ResultE<String>().execute(e ->
                e.setSuccess("executorService")
        );

    }

}
