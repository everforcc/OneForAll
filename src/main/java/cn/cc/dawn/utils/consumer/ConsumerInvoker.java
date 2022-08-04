/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-04 10:36
 * Copyright
 */

package cn.cc.dawn.utils.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * consumer调用操作
 */
@EnableAsync
@Component
@Slf4j
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
public class ConsumerInvoker<T> {

    /**
     * 异步调用操作
     *
     * @param t                 操作实体
     * @param invokeConsumer    需要异步执行的方法
     * @param exceptionConsumer 异步执行发生异常时需要执行的操作
     * @param finallyConsumer   异步执行结束后 finally 需要执行的操作
     * @return 异步调用操作对象
     */
    @Async
    public CompletableFuture<T> asyncInvoke(T t,
                                            Consumer<T> invokeConsumer,
                                            Consumer<T> exceptionConsumer,
                                            Consumer<T> finallyConsumer) {
        try {
            log.info("进入 异步 操作");
            Thread.sleep(6 * 1000);
            invokeConsumer.accept(t);
        } catch (Exception e) {
            log.error("异步 调用操作异常", e);
            if (exceptionConsumer != null) {
                exceptionConsumer.accept(t);
            }
        } finally {
            log.info("进入 异步 finally 操作");
            if (finallyConsumer != null) {
                finallyConsumer.accept(t);
            }
        }
        return CompletableFuture.completedFuture(t);
    }

    /**
     * 同步调用操作
     *
     * @param t                 操作实体
     * @param invokeConsumer    需要同步执行的方法
     * @param exceptionConsumer 同步执行发生异常时需要执行的操作
     * @param finallyConsumer   同步执行结束后 finally 需要执行的操作
     */
    public void syncInvoke(T t,
                           Consumer<T> invokeConsumer,
                           Consumer<T> exceptionConsumer,
                           Consumer<T> finallyConsumer) {
        try {
            log.info("进入 同步 操作");
            Thread.sleep(6 * 1000);
            invokeConsumer.accept(t);
        } catch (Exception e) {
            log.error("同步  调用操作异常", e);
            if (exceptionConsumer != null) {
                exceptionConsumer.accept(t);
            }
        } finally {
            log.info("进入 同步 finally 操作");
            if (finallyConsumer != null) {
                finallyConsumer.accept(t);
            }
        }
    }

}
