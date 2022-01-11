package com.kakao.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:mustache.properties"})
public class CafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeApplication.class, args);
	}

}
