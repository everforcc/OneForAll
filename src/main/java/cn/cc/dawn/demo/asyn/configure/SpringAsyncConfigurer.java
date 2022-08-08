/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 12:09
 * Copyright
 */

package cn.cc.dawn.demo.asyn.configure;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
class SpringAsyncConfigurer extends AsyncConfigurerSupport {

    /**
     * 线程配置
     *
     * @return 线程对戏那个
     */
    @Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(3);
        threadPool.setMaxPoolSize(3);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setAwaitTerminationSeconds(60 * 15);
        return threadPool;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    /**
     * 处理异常
     *
     * @return 处理异常的对象
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    /* 配置线程，一般项目下面二选一（多线程或者单线程） */

    /**
     * 可以配置多个线程池-1
     *
     * @return 线程
     */
    @Bean(name = "threadPoolTaskExecutor1")
    public Executor threadPoolTaskExecutor1() {
        return new ThreadPoolTaskExecutor();
    }

    /**
     * 可以配置多个线程池-2
     *
     * @return 线程
     */
    @Bean(name = "threadPoolTaskExecutor2")
    public Executor threadPoolTaskExecutor2() {
        return new ThreadPoolTaskExecutor();
    }


    /**
     * 一个线程池
     *
     * @return 线程
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        return executor;
    }

}