package com.kakao.cafe;

import com.kakao.cafe.user.adapter.in.FindUserController;
import com.kakao.cafe.user.adapter.in.SignUpController;
import com.kakao.cafe.user.adapter.out.MemoryUserRepository;
import com.kakao.cafe.user.application.FindUserService;
import com.kakao.cafe.user.application.SignUpService;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public FindUserController findUserController() {
        return new FindUserController(findUserService());
    }

    @Bean
    public SignUpController signUpController() {
        return new SignUpController(signUpService());
    }

    @Bean
    public FindUserService findUserService() {
        return new FindUserService(loadUserPort());
    }

    @Bean
    public SignUpService signUpService() {
        return new SignUpService(saveUserPort());
    }

    @Bean
    public SaveUserPort saveUserPort() {
        return new MemoryUserRepository();
    }

    @Bean
    public LoadUserPort loadUserPort() {
        return new MemoryUserRepository();
    }


}
