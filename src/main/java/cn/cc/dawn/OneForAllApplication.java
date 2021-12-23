package cn.cc.dawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 缓存
@EnableCaching
// FeignClients
@EnableFeignClients
public class OneForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneForAllApplication.class, args);
	}

}
