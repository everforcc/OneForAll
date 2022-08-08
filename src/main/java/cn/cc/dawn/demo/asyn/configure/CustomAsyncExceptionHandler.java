/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 12:12
 * Copyright
 */

package cn.cc.dawn.demo.asyn.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 异常处理类
 */
@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        log.info("Exception message - " + throwable.getMessage());
        log.info("Method name - " + method.getName());
        for (Object param : obj) {
            log.info("Parameter value - " + param);
        }
    }

}