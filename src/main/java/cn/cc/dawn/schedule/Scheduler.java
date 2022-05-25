package cn.cc.dawn.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

import cn.cc.dawn.utils.data.redis.RedisLockConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Yukino
 * 2020/5/12
 */
@Slf4j
@Component
public class Scheduler {

    @Resource
    private RedissonClient redissonClient;

    @Autowired
    private ExecutorService multiThread;

    /**
     * 用spring的定时任务来执行，
     */

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



    //@Scheduled(cron = "0 0/1 * * * ?",fixedRate = 5000)
    //@Scheduled(cron = "0/5 0/2 * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    /**
     * 线程内的日志打印有误
     *
     * TODO lock.lock(); 的等待时间
     */
    //@Scheduled(cron = "0/5 * * * * ?")
    public void bilibili_all_album() {

        multiThread.execute(new Runnable() {
            @Override
            public void run() {
                String threadName = dateFormat.format(new Date());
                log.info("The time is now {}", threadName);
                RLock lock = redissonClient.getLock(RedisLockConstant.BILIBILI_ALBUM_ALL);
                boolean flag = true;
                try {
                    lock.lock();
                    //flag = lock.tryLock(10,5, TimeUnit.SECONDS);
                    log.info("线程 [{}] bilibili获取相册定时任务 ==> 启动 是否获取到锁: {}",threadName,dateFormat.format(new Date()));
                    if(flag){
                        //log.info("线程 [{}] 睡眠前 {}",threadName, DateUtils.nowTime(DateFormatConstant.yyyy_MM_dd_HH_mm_ss));
                        Thread.sleep(15 * 1000);
                        //log.info("线程 [{}] 睡眠后 {}",threadName, DateUtils.nowTime(DateFormatConstant.yyyy_MM_dd_HH_mm_ss));
                    }else {
                        //log.info("线程 [{}] bilibili获取相册定时任务 ==> 启动 获取锁: 失败",threadName);
                    }
                    flag = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //if(flag){
                    if(flag){
                        //log.info("线程 [{}] 未获取到锁",threadName);
                    }else {
                        //log.info("线程 [{}] 准备解锁",threadName);
                    }
                    if(lock.isHeldByCurrentThread()){
                        //log.info("线程 [{}] 尝试关闭锁",threadName);
                        lock.unlock();
                        log.info("线程 [{}] bilibili获取相册定时任务 ==> 解锁: {}  {}",threadName,threadName,dateFormat.format(new Date()));
                    }
                    //}
                }
            }
        });

    }

}
