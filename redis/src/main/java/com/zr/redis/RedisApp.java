package com.zr.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName
 * @Description
 * @Author
 * @Date 2019/11/15
 */
@SpringBootApplication

public class RedisApp {
	public static void main(String[] args) {
		SpringApplication.run(RedisApp.class, args);
	}
}
