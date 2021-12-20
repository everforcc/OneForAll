package cn.cc.dawn.aop.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author everforcc 2021-09-25
 */
@Aspect
@Service
public class AOPRedisProcess {

    @Pointcut("execution(* cn.cc.dawn.business.aopredis..RedisProcess*(..)) && args(key)")
    public void pointCut(String key){

    }

    @Before("pointCut(key)")
    public void beforeLog(String key) {
        System.out.println("开始执行前检测剩余时间"+key);
        if(RedisConfig.map.get(key) < RedisConfig.leastTime){
            RedisConfig.map.put(key,RedisConfig.defalutTime);
        }
    }
    //    环绕通知
    @Around("pointCut(key)")
    public Object aroundLog(ProceedingJoinPoint joinpoint,String key) {
        Object result = null;
        try {
            System.out.println("环绕检测开始");
            if(RedisConfig.map.get(key) < RedisConfig.leastTime){
                RedisConfig.map.put(key,RedisConfig.defalutTime);
            }
            long start = System.currentTimeMillis();

            //有返回参数 则需返回值
            result =  joinpoint.proceed();
            System.out.println("执行返回值: [" + result + "]");
            long end = System.currentTimeMillis();
            System.out.println("总共执行时长: [" + (end - start) + "] 毫秒");
            System.out.println("环绕通知结束 日志记录");
        } catch (Throwable t) {
            System.out.println("出现错误");
        }
        return result;
    }
    //    方法执行完后通知
    @After("pointCut(key)")
    public void afterLog(String key) {

    }
    //    执行成功后通知
    @AfterReturning("pointCut(key)")
    public void afterReturningLog(String key) {

    }
    //    抛出异常后通知
    @AfterThrowing("pointCut(key)")
    public void afterThrowingLog(String key) {

    }

}
