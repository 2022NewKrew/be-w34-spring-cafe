package com.kakao.cafe.config;

import com.kakao.cafe.aop.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(LogAspect.class);
    }
}
