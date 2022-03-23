package cn.cc.dawn.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceAop {

    /**
     * service层用来处理逻辑和数据库交互
     * - [ ] 在这里处理公共字段
     *
     */

    @Before("execution(* cn.cc.dawn..*.service..*.save(..))")
    public void beforeSave(final JoinPoint joinPoint) {
      //在save前,处理公共字段
        log.info("service前置处理，等待处理公共字段...");
    }

    @After("execution(* cn.cc.dawn..*.service..*.save(..))")
    public void afterSave(final JoinPoint joinPoint) {
        //在save前,处理公共字段
        log.info("service前置处理，等待处理公共字段...");
    }

}
