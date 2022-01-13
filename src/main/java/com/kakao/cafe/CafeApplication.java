package com.kakao.cafe;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeApplication.class, args);
    }

    @RequiredArgsConstructor
    @Component
    class TestApplicationRunner implements ApplicationRunner {

        private final ApplicationContext applicationContext;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        }
    }

}
