package cn.cc.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 事务注解开关，非必要
@EnableTransactionManagement
@SpringBootApplication
// 缓存 CacheManager
@EnableCaching
@EnableScheduling
// FeignClients
@EnableFeignClients
@MapperScan(value = "cn.cc.dawn.**.dao") //扫描包 找找文档， ** 任意多级包名, 正式使用定好业务代码位置携程数据，全局写启动扫描慢
public class OneForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneForAllApplication.class, args);
	}

}
