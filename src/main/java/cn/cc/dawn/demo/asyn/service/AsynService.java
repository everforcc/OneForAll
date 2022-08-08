/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 11:39
 * Copyright
 */

package cn.cc.dawn.demo.asyn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class AsynService {

    /**
     * 不带返回值
     */
    @Async  //标注使用
    public void asyncMethodWithVoidReturnType() {
        log.info("Execute method asynchronously. {}", Thread.currentThread().getName());
    }

    /**
     * 带返回值
     *
     * @return 返回future
     */
    @Async
    public Future<String> asyncMethodWithReturnType() {
        log.info("Execute method asynchronously. {}", Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }

    /**
     * 方法内出现异常
     */
    @Async
    public void asyncMethodWithVoidException() {
        log.info("异常方法. {}", Thread.currentThread().getName());
        throw new RuntimeException("自定义异常...");
    }


    /**
     * 使用自定义线程池
     */
    @Async("threadPoolTaskExecutor1")
    public void asyncMethodWithUserThread() {
        log.info("Execute method asynchronously. {}", Thread.currentThread().getName());
    }

}
