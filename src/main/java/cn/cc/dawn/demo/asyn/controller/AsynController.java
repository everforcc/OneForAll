/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 11:34
 * Copyright
 */

package cn.cc.dawn.demo.asyn.controller;

import cn.cc.dawn.demo.asyn.service.AsynService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RequestMapping("/open/asyn")
@RestController
public class AsynController {
    // 测试异步代码

    @Resource
    AsynService asynService;

    /**
     * 4. 没有返回值 异步测试
     */
    @GetMapping("/withOutReturn")
    public void withOutReturn() {
        log.info("不带返回值 异步测试 ");
        asynService.asyncMethodWithVoidReturnType();
    }

    /**
     * 5. 有返回值 异步测试
     */
    @GetMapping("/withReturn")
    public void withReturn() {
        log.info("带返回值 异步测试 {}", Thread.currentThread().getName());
        Future<String> future = asynService.asyncMethodWithReturnType();
        while (true) {  ///这里使用了循环判断，等待获取结果信息
            try {
                if (future.isDone()) {  //判断是否执行完毕
                    log.info("Result from asynchronous process - {}", future.get());
                    break;
                }
                log.info("Continue doing something else. ");
                Thread.sleep(100);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 6. 方法内出现异常
     */
    @GetMapping("/withException")
    public void withException() {
        log.info("方法内出现异常: 开始 ");
        asynService.asyncMethodWithVoidException();
        log.info("方法内出现异常: 结束 ");
    }

    /**
     * 7. 使用自定义线程池
     */
    @GetMapping("/withUserThread")
    public void withUserThread() {
        log.info("自定义线程池 ");
        asynService.asyncMethodWithUserThread();
    }

}
