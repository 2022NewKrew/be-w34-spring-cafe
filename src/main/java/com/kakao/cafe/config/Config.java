package com.kakao.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Mapper Mapper() {
        return new Mapper();
    }
}
