/**
 * @Description
 * @Author everforcc
 * @Date 2022-08-08 12:01
 * Copyright
 */

package cn.cc.dawn.demo.asyn.exception;

import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor {

    private AsyncTaskExecutor executor;

    public ExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable task) {
        executor.execute(createWrappedRunnable(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        // 用独立的线程来包装，@Async其本质就是如此
        executor.execute(createWrappedRunnable(task), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        //用独立的线程来包装，@Async其本质就是如此。
        return executor.submit(createWrappedRunnable(task));
    }

    @Override
    public Future submit(final Callable task) {
        //用独立的线程来包装，@Async其本质就是如此。
        return executor.submit(createCallable(task));
    }

    private Callable createCallable(final Callable task) {
        return () -> {
            try {
                return task.call();
            } catch (Exception ex) {
                handle(ex);
                throw ex;
            }
        };
    }

    private Runnable createWrappedRunnable(final Runnable task) {
        return new Runnable() {
            public void run() {
                try {
                    task.run();
                } catch (Exception ex) {
                    handle(ex);
                }
            }
        };
    }

    /**
     * 具体处理异常的地方
     *
     * @param ex 异常
     */
    private void handle(Exception ex) {
        //具体的异常逻辑处理的地方
        System.err.println("Error during @Async execution: " + ex);
    }

}
