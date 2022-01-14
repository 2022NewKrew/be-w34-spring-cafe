package com.kakao.cafe.application.user;

import com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.user.StoreUserInfoAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserInfoRepository;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.GetUserInfoPort;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.application.user.port.out.UpdateUserInfoPort;
import com.kakao.cafe.application.user.service.GetUserInfoService;
import com.kakao.cafe.application.user.service.SignUpUserService;
import com.kakao.cafe.application.user.service.UpdateUserInfoService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcUserConfig implements WebMvcConfigurer {

    public final DataSource dataSource;

    public MvcUserConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserInfoRepository userInfoRepository() {
        return new JdbcUserInfoRepository(dataSource);
    }

    @Bean
    public GetUserInfoPort getUserInfoPort() {
        return new StoreUserInfoAdapter(userInfoRepository());
    }

    @Bean
    public RegisterUserPort registerUserPort() {
        return new StoreUserInfoAdapter(userInfoRepository());
    }

    @Bean
    public UpdateUserInfoPort updateUserInfoPort() {
        return new StoreUserInfoAdapter(userInfoRepository());
    }

    @Bean
    public GetUserInfoUseCase getUserInfoUseCase() {
        return new GetUserInfoService(getUserInfoPort());
    }

    @Bean
    public SignUpUserUseCase signUpUserUseCase() {
        return new SignUpUserService(registerUserPort());
    }

    @Bean
    public UpdateUserInfoUseCase updateUserInfoUseCase() {
        return new UpdateUserInfoService(updateUserInfoPort());
    }
}
