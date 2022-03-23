package cn.cc.dawn.aop;

import cn.cc.dawn.utils.entity.ResultE;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author everforcc 2021-09-25
 */
@Aspect
@Slf4j
@Service
public class LogAop {

    /**
     * ProceedingJoinPoint joinpoint
     */

    //@Pointcut("execution(* cn.cc.dawn.business.user.UserService.GetDemoUser(..)) && args(id)")
    //@Pointcut("execution(* cn.cc.dawn.business.aoplog..tAOPLog(..)) && args(id)")

    /**
     * ..*表示子孙包
     * .*(..) 表示所有参数的方法
     */
    @Pointcut("execution(* cn.cc.dawn.demo..*.controller..*.*(..))")
    public void pointCut(){

    }

    // 配置连接点 方法开始执行时通知
    @Before("pointCut()")
    public void beforeLog() {
        log.info("当前线程id:" + Thread.currentThread ().getId());
        //log.info("开始执行前置通知  日志记录:");
    }
    //    方法执行完后通知
    @After("pointCut()")
    public void afterLog() {
        //log.info("开始执行后置通知 日志记录:");
    }
    //    执行成功后通知
    @AfterReturning("pointCut()")
    public void afterReturningLog() {
        //log.info("方法成功执行后通知 日志记录:");
    }
    //    抛出异常后通知
//    @AfterThrowing("pointCut()")
//    public void afterThrowingLog() {
//        log.info("方法抛出异常后执行通知 日志记录");
//    }

    //    环绕通知
    @Around("pointCut()")
    public Object aroundLog(ProceedingJoinPoint joinpoint) {
        Object result = null;
        try {
            //log.info("环绕通知开始 日志记录: []");
            long start = System.currentTimeMillis();

            //有返回参数 则需返回值 (ResultE)
            result =  joinpoint.proceed();
            //log.info("执行返回值: [" + result.toString() + "]");
            long end = System.currentTimeMillis();
            //log.info("总共执行时长: [" + (end - start) + "] 毫秒");
            //log.info("环绕通知结束 日志记录");
        } catch (Throwable t) {
            t.printStackTrace();
            log.info("出现错误");
        }
        return result;
    }

}
