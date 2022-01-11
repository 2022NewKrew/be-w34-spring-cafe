package com.kakao.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeApplication {
    
    public static void main(String[] args) {
//        logger.info("{} Spring API: {}", msg, v);
        SpringApplication.run(CafeApplication.class, args);
    }

}
