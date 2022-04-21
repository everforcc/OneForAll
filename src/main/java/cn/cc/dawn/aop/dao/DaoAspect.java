package cn.cc.dawn.aop.dao;

import cn.cc.dawn.aop.dao.impl.DaoAspectImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DaoAspect extends DaoAspectImpl {

    @Before("execution(* cn.cc.dawn..*.service..*.insert(..))")
    public void beforeSave(final JoinPoint joinPoint) {
        save(joinPoint);
    }

}
