package com.kakao.cafe.config;

import com.kakao.cafe.user.application.port.in.UserCommonQueryUseCase;
import com.kakao.cafe.user.application.port.in.UserSignUpUseCase;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import com.kakao.cafe.user.application.port.out.UserCommonQueryPort;
import com.kakao.cafe.user.application.port.out.UserRegistrationPort;
import com.kakao.cafe.user.application.port.out.UserUpdatePort;
import com.kakao.cafe.user.application.service.UserCommonQueryService;
import com.kakao.cafe.user.application.service.UserSignUpService;
import com.kakao.cafe.user.application.service.UserUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {


    @Bean
    public UserSignUpUseCase userSignUpService(UserRegistrationPort userRegistrationPort) {
        return new UserSignUpService(userRegistrationPort);
    }

    @Bean
    public UserUpdateUseCase userUpdateService(UserUpdatePort userUpdatePort) {
        return new UserUpdateService(userUpdatePort);
    }

    @Bean
    public UserCommonQueryUseCase userCommonQueryUseCase(UserCommonQueryPort userCommonQueryPort) {
        return new UserCommonQueryService(userCommonQueryPort);
    }
}
