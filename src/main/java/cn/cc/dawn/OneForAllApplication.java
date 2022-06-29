package cn.cc.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 事务注解开关，非必要
@EnableTransactionManagement
// 开启 @PreAuthorize注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
// 缓存 CacheManager
@EnableCaching
@EnableScheduling
// FeignClients
@EnableFeignClients
@EnableRetry
@MapperScan(value = "cn.cc.dawn.**.dao") //扫描包 找找文档， ** 任意多级包名, 正式使用定好业务代码位置携程数据，全局写启动扫描慢
public class OneForAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneForAllApplication.class, args);
    }

}
