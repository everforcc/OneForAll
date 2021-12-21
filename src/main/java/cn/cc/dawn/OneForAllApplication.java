package cn.cc.dawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OneForAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneForAllApplication.class, args);
	}

}
