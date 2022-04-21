package cn.cc.dawn.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * Yukino
 * 2020/5/12
 */
@Slf4j
@Component
public class Scheduler {

    /**
     * 用spring的定时任务来执行，
     */

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron = "0 0/1 * * * ?",fixedRate = 5000)
    @Scheduled(cron = "0/5 0/2 * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
