package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeApplication {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(CafeApplication.class);
		SpringApplication.run(CafeApplication.class, args);
		log.info("first log message");
	}

}
