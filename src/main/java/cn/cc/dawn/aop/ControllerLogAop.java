package cn.cc.dawn.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLogAop {

    /**
     * controller，处理
     * 打印日志入参和返参
     */

    @Before("execution(* cn.cc.dawn..*.controller..*.*(..))")
    public void controllerParams(final JoinPoint joinPoint) {
       log.info("打印参数");
    }

}
