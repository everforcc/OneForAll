package cn.cc.dawn.common.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author everforcc 2021-09-25
 */
@Aspect
@Service
public class AOPRedisCover {

    @Pointcut("execution(* cn.cc.dawn.business.cacheredis..RedisCover*(..)) && args(id,name)")
    public void pointCut(int id,String name){}

    @Before("pointCut(id,name)")
    public void beforeLog(int id,String name) {
        System.out.println("开始执行前置 设置redis"+id);
        RedisConfig.map.put(name,id);
    }

    @After("pointCut(id,name)")
    public void afterLog(int id,String name) {
        System.out.println("开始执行后置通知");
    }

    @AfterReturning("pointCut(id,name)")
    public void afterReturningLog(int id,String name) {
        System.out.println("方法成功执行后移出redis");
        RedisConfig.map.remove(name);
    }

    @AfterThrowing("pointCut(id,name)")
    public void afterThrowingLog(int id,String name) {
        System.out.println("方法抛出异常后清除redis");
        RedisConfig.map.remove(name);
    }

    //    环绕通知
    @Around("pointCut(id,name)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, int id,String name) {
        Object result = null;
        try {
            System.out.println("环绕通知开始 日志记录: [" + id + "]");
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

}
