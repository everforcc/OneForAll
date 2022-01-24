package cn.cc.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 缓存 CacheManager
@EnableCaching
// FeignClients
@EnableFeignClients
@MapperScan(value = "cn.cc.dawn.**.dao") //扫描包 找找文档， ** 任意多级包名
public class OneForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneForAllApplication.class, args);
	}

}
