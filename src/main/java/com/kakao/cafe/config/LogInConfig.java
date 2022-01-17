package com.kakao.cafe.config;

import com.kakao.cafe.login.application.port.in.LoginUseCase;
import com.kakao.cafe.login.application.port.out.LoadLoginInfoPort;
import com.kakao.cafe.login.application.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogInConfig {
    @Bean
    public LoginUseCase userLoginService(LoadLoginInfoPort loadLoginInfoPort) {
        return new LoginService(loadLoginInfoPort);
    }
}
